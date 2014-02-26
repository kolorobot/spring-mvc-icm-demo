package com.github.kolorobot.icm.incident;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.AccountRepository;
import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.incident.Incident.Status;

@Service
class IncidentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentService.class);

    private IncidentRepository incidentRepository;
    private AccountRepository accountRepository;
    private AuditRepository auditRepository;

    @Inject
    IncidentService(IncidentRepository incidentRepository, AuditRepository auditRepository, AccountRepository accountRepository) {
        this.incidentRepository = incidentRepository;
        this.auditRepository = auditRepository;
        this.accountRepository = accountRepository;
    }

    public List<Incident> getIncidents(User user, Status status) {
        if (user.isInRole(Account.ROLE_USER)) {
            return incidentRepository.findAllByCreatorIdAndStatus(user.getAccountId(), status);
        }
        if (user.isInRole(Account.ROLE_EMPLOYEE)) {
            return incidentRepository.findAllByAssigneeIdOrCreatorIdAndStatus(user.getAccountId(), status);
        }
        if (user.isInRole(Account.ROLE_ADMIN)) {
            return incidentRepository.findAllByStatus(status);
        }
        return Lists.newArrayList();
    }

    public Incident getIncident(User user, Long incidentId) {
        if (user.isInRole(Account.ROLE_USER)) {
            return getUserIncident(user, incidentId);
        }
        if (user.isInRole(Account.ROLE_EMPLOYEE)) {
            return getEmployeeIncident(user, incidentId);
        }
        return getOne(user, incidentId);
    }

    public List<Audit> getAudits(Incident incident) {
        return auditRepository.findAll(incident.getId());
    }

    public Account getCreator(Incident incident) {
        return accountRepository.findOne(incident.getCreatorId());
    }

    public Account getAssignee(Incident incident) {
        return accountRepository.findOne(incident.getAssigneeId());
    }

    @Transactional
    public Incident create(User user, IncidentForm incidentForm) {

        Address incidentAddress = new Address();
        incidentAddress.setCityLine(incidentForm.getCityLine());
        incidentAddress.setAddressLine(incidentForm.getAddressLine());

        Incident incident = new Incident();
        incident.setAddress(incidentAddress);
        incident.setDescription(incidentForm.getDescription());
        incident.setIncidentType(incidentForm.getType());
        incident.setCreatorId(user.getAccountId());
        incident.setCreated(new Date());

        incidentRepository.save(incident);
        LOGGER.info("Created an incident: " + incident.toString());
        return incident;
    }

    @Transactional
    public Audit addAudit(User user, Incident incident, AuditForm auditForm) {

        Audit audit = new Audit();
        audit.setIncidentId(incident.getId());
        audit.setDescription(auditForm.getDescription());
        audit.setCreated(new Date());
        audit.setCreatorId(user.getAccountId());


        // update the status
        Status newStatus = auditForm.getNewStatus();
        if (newStatus == null) {
            newStatus = incident.getStatus();
        }
        audit.setPreviousStatus(incident.getStatus());
        audit.setStatus(newStatus);
        incident.setStatus(newStatus);

        // assign someone to the incident
        Long assigneeId = auditForm.getAssigneeId();
        if (assigneeId != null) {
            incident.setAssigneeId(assigneeId);
        }

        incidentRepository.update(incident);
        auditRepository.save(audit);
        LOGGER.info("Created an audit: " + incident.toString());
        return audit;
    }

    //
    // internal helpers
    //

    private Incident getUserIncident(User user, Long incidentId) {
        return incidentRepository.findOneByIdAndCreatorId(incidentId, user.getAccountId());
    }

    private Incident getEmployeeIncident(User user, Long incidentId) {
        return incidentRepository.findOneByIdAndAssigneeIdOrCreatorId(incidentId, user.getAccountId());
    }

    private Incident getOne(User user, Long incidentId) {
        return incidentRepository.findOne(incidentId);
    }

    public List<Incident> search(String queryString) {
        String query = queryString.replaceAll("%", "").replaceAll("_", "");
        if (query.matches("[0-9]*")) {
            long id = Long.parseLong(query);
            return Lists.newArrayList(incidentRepository.findOne(id));
        }
        return incidentRepository.search("%" + query + "%");
    }
}
