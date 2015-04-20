package com.github.kolorobot.icm.incident;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.AccountRepository;
import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.dashboard.IncidentCounts;
import com.github.kolorobot.icm.dashboard.IncidentCountsRepository;
import com.github.kolorobot.icm.files.File;
import com.github.kolorobot.icm.files.FilesRepository;
import com.github.kolorobot.icm.incident.Incident.Status;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static com.github.kolorobot.icm.account.Account.ROLE_ADMIN;
import static com.github.kolorobot.icm.account.Account.ROLE_EMPLOYEE;
import static com.github.kolorobot.icm.incident.Incident.Status.*;

@Service
public class IncidentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentService.class);

    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuditRepository auditRepository;
    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private IncidentCountsRepository incidentCountsRepository;

    public List<Account> getAccountsInRole(String role) {
        if (role == null) {
            return accountRepository.findAll();
        }
        return accountRepository.findAllByRole(role);
    }

    public List<Incident> getIncidents(Status status) {
        if (status != null) {
            return incidentRepository.findAllByStatus(status);
        }
        return incidentRepository.findAll();
    }

    public Incident getIncident(long incidentId) {
        return incidentRepository.findOne(incidentId);
    }

    public List<Incident> getUserIncidents(long userId, Status status) {
        return getUserIncidents(accountRepository.findOne(userId).asUser(), status);
    }

    public List<Incident> getUserIncidents(User user, Status status) {
        if (user.isInRole(Account.ROLE_USER)) {
            return incidentRepository.findAllByCreatorIdAndStatus(user.getAccountId(), status);
        }
        // FIXME Security leak
        if (status == null) {
            return incidentRepository.findAll();
        } else {
            return incidentRepository.findAllByStatus(status);
        }

//        if (user.isInRole(Account.ROLE_EMPLOYEE)) {
//            return incidentRepository.findAllByAssigneeIdOrCreatorIdAndStatus(user.getAccountId(), status);
//        }
//        if (user.isInRole(Account.ROLE_ADMIN)) {
//            return incidentRepository.findAllByStatus(status);
//        }
//        return Lists.newArrayList();
    }

    public Incident getUserIncident(long userId, Long incidentId) {
        return incidentRepository.findOneByIdAndCreatorId(incidentId, accountRepository.findOne(userId).asUser().getAccountId());
    }

    public Incident getUserIncident(User user, Long incidentId) {
        if (user.isInRole(Account.ROLE_USER)) {
            return incidentRepository.findOneByIdAndCreatorId(incidentId, user.getAccountId());
        }
        if (user.isInRole(Account.ROLE_EMPLOYEE)) {
            return incidentRepository.findOneByIdAndAssigneeIdOrCreatorId(incidentId, user.getAccountId());
        }
        return incidentRepository.findOne(incidentId);
    }

    public List<Audit> getIncidentAudits(long incidentId) {
        return auditRepository.findAll(incidentId);
    }

    public Account getAccount(long accountId) {
        return accountRepository.findOne(accountId);
    }

    public List<File> getIncidentFiles(long incidentId) {
        return filesRepository.findAll("incident", incidentId);
    }

    @Transactional
    public Incident createIncident(long userId, String type, String description, String addressLine, String cityLine) {
        return createIncident(accountRepository.findOne(userId).asUser(), type, description, addressLine, cityLine);
    }

    @Transactional
    public Incident createIncident(User user, String type, String description, String addressLine, String cityLine) {

        Address incidentAddress = new Address();
        incidentAddress.setCityLine(cityLine);
        incidentAddress.setAddressLine(addressLine);

        Incident incident = new Incident();
        incident.setAddress(incidentAddress);
        incident.setIncidentType(type);
        incident.setDescription(description);
        incident.setCreatorId(user.getAccountId());
        incident.setCreated(randomDate());

        incidentRepository.save(incident);
        LOGGER.info("Created an incident: " + incident.toString());
        return incident;
    }

    public List<Status> getAvailableUserIncidentTransitions(long userId, long incidentId) {
        return getAvailableUserIncidentTransitions(accountRepository.findOne(userId).asUser(), incidentRepository.findOne(incidentId));
    }

    public List<Status> getAvailableUserIncidentTransitions(User user, Incident incident) {
        Status status = incident.getStatus();
        if (user.isInRole(ROLE_ADMIN)) {
            if (EnumSet.of(NOT_CONFIRMED, SOLVED).contains(status)) {
                return Lists.newArrayList(CLOSED, NEW);
            }
        } else if (user.isInRole(ROLE_EMPLOYEE)) {
            switch (status) {
                case NEW:
                    return Lists.newArrayList(CONFIRMED, NOT_CONFIRMED, SOLVED);
                case CONFIRMED:
                    return Lists.newArrayList(NEW, NOT_CONFIRMED, SOLVED);
                case NOT_CONFIRMED:
                    return Lists.newArrayList(NEW, CONFIRMED, SOLVED);
                case SOLVED:
                    return Lists.newArrayList(NEW, CONFIRMED, NOT_CONFIRMED);
                default:
                    break;
            }
        } else {
            throw new IllegalAccessError();
        }
        return null;
    }

    public List<Account> getAvailableUserIncidentAssignees(long userId, long incidentId) {
        return getAvailableUserIncidentAssignees(accountRepository.findOne(userId).asUser(), incidentRepository.findOne(incidentId));
    }

    public List<Account> getAvailableUserIncidentAssignees(User user, Incident incident) {
        if (user.isInRole(ROLE_ADMIN)
                && EnumSet.of(NEW, NOT_CONFIRMED, SOLVED).contains(incident.getStatus())) {
            return accountRepository.findAll();
        }
        return null;
    }

    @Transactional
    public Audit addUserIncidentAudit(Long userId, Long incidentId, Long assigneeId, Status newStatus, String description) {
        return addUserIncidentAudit(accountRepository.findOne(userId).asUser(), incidentRepository.findOne(incidentId), assigneeId, newStatus, description);
    }

    @Transactional
    public Audit addUserIncidentAudit(User user, Incident incident, Long assigneeId, Status newStatus, String description) {

        Audit audit = new Audit();
        audit.setIncidentId(incident.getId());
        audit.setDescription(description);
        audit.setCreated(new Date());
        audit.setCreatorId(user.getAccountId());


        // update the status
        if (newStatus == null) {
            newStatus = incident.getStatus();
        }
        audit.setPreviousStatus(incident.getStatus());
        audit.setStatus(newStatus);
        incident.setStatus(newStatus);

        // assign someone to the incident
        if (assigneeId != null) {
            incident.setAssigneeId(assigneeId);
        }

        incidentRepository.update(incident);
        auditRepository.save(audit);
        LOGGER.info("Created an audit: " + incident.toString());
        return audit;
    }

    public void setIncidentDescription(long incidentId, String description) {
        Incident incident = incidentRepository.findOne(incidentId);
        incident.setDescription(description);
        incidentRepository.update(incident);
    }

    public List<Incident> search(String queryString) {
        String query = queryString.replaceAll("%", "").replaceAll("_", "");
        if (query.matches("[0-9]*")) {
            long id = Long.parseLong(query);
            // FIXME May throw EmptyResultDataAccessException
//            try {
            return Lists.newArrayList(incidentRepository.findOne(id));
//            } catch (EmptyResultDataAccessException e) {
//                return Lists.newArrayList();
//            }
        }
        // FIXME Security leak possible
        return incidentRepository.search("%" + query + "%");
    }

    public IncidentCounts getIncidentCounts() {
        return incidentCountsRepository.incidentCounts();
    }

    //
    // internal helpers
    //

    private Date randomDate() {
        long day = 24L * 60L * 60L * 1000L;
        long month = 30L * day;
        long randomTime = day + (long) (Math.random() * ((month - day) + 1));
        Date date = new Date(System.currentTimeMillis() - randomTime);
        return date;
    }
}
