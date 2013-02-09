package com.github.kolorobot.icm.incident;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/incident")
class IncidentController {
	
	@Inject
	private IncidentService incidentService; 
	
	@RequestMapping("/create")
	public IncidentForm create() {
		return new IncidentForm();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String signup(UserDetails user, @Valid @ModelAttribute IncidentForm incidentForm, Errors errors) {
		if (errors.hasErrors()) {
			return null;
		}
		
		Incident incident = new Incident();
		incident.setDescription(incidentForm.getDescription());
		incident.setIncidentType(incidentForm.getType());
		incident.setIncidentAddress(incidentForm.getAddress());
		
		incidentService.createIncident(user, incident);
		
		return "redirect:/";
	}
	
}
