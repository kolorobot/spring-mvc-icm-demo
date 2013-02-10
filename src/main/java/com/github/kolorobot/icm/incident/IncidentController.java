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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.kolorobot.icm.support.web.Message;

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
	public String create(UserDetails user, @Valid @ModelAttribute IncidentForm incidentForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return null;
		}
		
		Incident incident = new Incident();
		incident.setDescription(incidentForm.getDescription());
		incident.setIncidentType(incidentForm.getType());
		incident.setIncidentAddress(incidentForm.getAddress());
		
		incidentService.createIncident(user, incident);
		
		ra.addFlashAttribute(Message.MESSAGE_ATTRIBUTE, new Message("New incident successfully created", Message.Type.SUCCESS));
		
		return "redirect:/incident/list";
	}
	
	@RequestMapping(value = "/list")
	@ModelAttribute(value = "incidents")
	public List<Incident> list() {
		// FIXME Only current user's incidents
		return incidentRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", headers = "X-Requested-With=XMLHttpRequest")
	@Transactional
	public String detailsAjax(@PathVariable("id") Long id, Model model) {
		// FIXME Only current user's incident
		Incident incident = incidentRepository.findOne(id);
		List<Audit> audits = new ArrayList<Audit>(incident.getAudits());
		model.addAttribute("incident", incident);
		model.addAttribute("audits", audits);
		return "incident/detailsAjax";
	}
	
	@RequestMapping(value = "/{id}")
	@Transactional
	public String details(@PathVariable("id") Long id, Model model) {
		// FIXME Only current user's incident
		Incident incident = incidentRepository.findOne(id);
		List<Audit> audits = new ArrayList<Audit>(incident.getAudits());
		model.addAttribute("incident", incident);
		model.addAttribute("audits", audits);
		return "incident/details";
	}
	
	@RequestMapping(value = "search")
	public String search(@RequestParam String q) {
		// FIXME Not handled conversion error
		// FIXME Not handled any error
		Long incidentId = Long.valueOf(q);
		return "forward:/incident/" + incidentId;
	}
}
