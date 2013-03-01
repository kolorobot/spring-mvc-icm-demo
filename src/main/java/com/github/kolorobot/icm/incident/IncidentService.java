package com.github.kolorobot.icm.incident;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.AccountRepository;
import com.github.kolorobot.icm.account.Address;
import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.incident.Incident.Status;

@Service
class IncidentService {
	
	@Inject
	private IncidentRepository incidentRepository;
	
	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private AuditRepository auditRepository;
	
	public List<Incident> getIncidents(User user) {
		return incidentRepository.findAllByOperatorId(user.getOperatorId());
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
	
	@Transactional
	public Incident create(User user, IncidentForm incidentForm)  {
		
		Address incidentAddress = new Address();
		incidentAddress.setCityLine(incidentForm.getAddressLine());
		incidentAddress.setAddressLine(incidentForm.getAddressLine());
		incidentAddress.setOperatorId(user.getOperatorId());
		
		Incident incident = new Incident();
		incident.setAddress(incidentAddress);
		incident.setDescription(incidentForm.getDescription());
		incident.setIncidentType(incidentForm.getType());
		incident.setOperatorId(user.getOperatorId());
		incident.setCreator(accountRepository.findOne(user.getAccountId()));
		incident.setCreated(randomDate());
		
		return incidentRepository.save(incident);
	}

	@Transactional
	public Audit addAudit(User user, Incident incident, AuditForm auditForm) {
		
		Audit audit = new Audit();
		audit.setDescription(auditForm.getDescription());	
		audit.setCreated(new Date());
		audit.setCreator(accountRepository.findOne(user.getAccountId()));
		audit.setPreviousStatus(incident.getStatus());
		audit.setOperatorId(user.getOperatorId());
		
		// update the status
		Status status = auditForm.getNewStatus();
		if (status == null) {
			status = incident.getStatus();
		}
		audit.setStatus(status);
		
		// assign someone to the incident
		Long assigneeId = auditForm.getAssigneeId();
		if (assigneeId != null) {
			incident.setAssignee(accountRepository.findOne(assigneeId));
		}
		incident.addAudit(audit);
		
		return auditRepository.save(audit);
	}
	
	//
	// internal helpers
	//
	
	private Incident getUserIncident(User user, Long incidentId) {
		return incidentRepository.findOneByIdAndCreatorId(incidentId, user.getAccountId(), user.getOperatorId());
	}
	
	private Incident getEmployeeIncident(User user, Long incidentId) {
		return incidentRepository.findOneByIdAndAssigneeIdOrCreatorId(incidentId, user.getAccountId(), user.getOperatorId());
	}
	
	private Incident getOne(User user, Long incidentId) {
		return incidentRepository.findOne(incidentId, user.getOperatorId());
	}
	
	private Date randomDate() {
		long day = 24L * 60L * 60L * 1000L;
		long month = 30L * day;
		long randomTime = day	+ (long) (Math.random() * ((month - day) + 1));
		Date date = new Date(System.currentTimeMillis() - randomTime);
		return date;
	}
}
