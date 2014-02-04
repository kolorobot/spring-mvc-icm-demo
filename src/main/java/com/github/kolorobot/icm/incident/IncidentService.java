package com.github.kolorobot.icm.incident;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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

	@Inject
	private IncidentRepository incidentRepository;
	
	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private AuditRepository auditRepository;
	
	public List<Incident> getIncidents(User user) {
		return incidentRepository.findAll();
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
	public Incident create(User user, IncidentForm incidentForm)  {
		
		Address incidentAddress = new Address();
		incidentAddress.setCityLine(incidentForm.getAddressLine());
		incidentAddress.setAddressLine(incidentForm.getAddressLine());
		
		Incident incident = new Incident();
		incident.setAddress(incidentAddress);
		incident.setDescription(incidentForm.getDescription());
		incident.setIncidentType(incidentForm.getType());
		incident.setCreatorId(user.getAccountId());
		incident.setCreated(randomDate());

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
		audit.setPreviousStatus(incident.getStatus());

		// update the status
		Status newStatus = auditForm.getNewStatus();
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
	
	private Date randomDate() {
		long day = 24L * 60L * 60L * 1000L;
		long month = 30L * day;
		long randomTime = day	+ (long) (Math.random() * ((month - day) + 1));
		Date date = new Date(System.currentTimeMillis() - randomTime);
		return date;
	}


}
