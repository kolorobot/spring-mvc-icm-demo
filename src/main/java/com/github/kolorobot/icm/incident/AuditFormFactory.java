package com.github.kolorobot.icm.incident;

import com.github.kolorobot.icm.account.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
class AuditFormFactory {
	
	@Inject
	private IncidentService incidentService;
	
	public AuditForm createAuditForm(User user, Incident incident) {
		AuditForm form = new AuditForm();
		form.setOldStatus(incident.getStatus());
		form.setAvailableStatuses(incidentService.getAvailableUserIncidentTransitions(user, incident));
		form.setAvailableEmployees(incidentService.getAvailableUserIncidentAssignees(user, incident));
		return form;
	}
	

}
