package com.github.kolorobot.icm.incident;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/incident")
class IncidentController {
	
	@Inject
	private IncidentService incidentService; 
	
	@Inject
	private IncidentRepository incidentRepository;
	
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
	
	@RequestMapping(value = "/list")
	@ModelAttribute(value = "incidents")
	public List<Incident> list() {
		// FIXME Only current user's incidents
		return incidentRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}")
	public String details(@PathVariable("id") Long id, Model model) {
		// FIXME Only current user's incident
		model.addAttribute("incident", incidentRepository.findOne(id));
		return "incident/details";
	}
	
	@RequestMapping(value = "/{id}/audit")
	@Transactional
	public String auditList(@PathVariable("id") Long id, Model model) {
		// FIXME Only current user's incident
		// FIXME Get audits sorted
		List<Audit> audits = new ArrayList<Audit>(incidentRepository.findOne(id).getAudits());
		model.addAttribute("audits", audits);
		return "incident/audit/list";
	}
}
