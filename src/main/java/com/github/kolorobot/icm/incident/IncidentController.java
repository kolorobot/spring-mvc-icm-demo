package com.github.kolorobot.icm.incident;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.error.AjaxRequestException;
import com.github.kolorobot.icm.support.web.MessageHelper;

@Controller
@RequestMapping("/incident")
class IncidentController {
	
	@Inject
	private IncidentService incidentService;
	
	@Inject
	private AuditFormFactory auditFormFactory;
	
	@RequestMapping("/create")
	public IncidentForm create() {
		return new IncidentForm();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User user, @Valid @ModelAttribute IncidentForm incidentForm, Errors errors, RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {			
			MessageHelper.addErrorAttribute(model, "incident.create.failed");
			return null;
		}
		Incident incident = incidentService.create(user, incidentForm);
		MessageHelper.addSuccessAttribute(ra, "incident.create.success", incident.getId());
		return "redirect:/incident/list";
	}	
	
	@RequestMapping(value = "/list")
	@ModelAttribute(value = "incidents")
	public List<Incident> list(User user) {
		return incidentService.getIncidents(user);	
	}
	
	@RequestMapping(value = "/{id}", headers = "X-Requested-With=XMLHttpRequest")
	public String detailsAjax(User user, @PathVariable("id") Long id, Model model) {
		Incident incident = getIncident(user, id);
		if (incident == null) {			
			throw new AjaxRequestException("Exception 0x156DDE");
		}
		model.addAttribute("incident", incident);
		return "incident/detailsAjax";
	}

	@RequestMapping(value = "/{id}")
	public String details(User user, @PathVariable("id") Long id, Model model) {
		Incident incident = getIncident(user, id);
		if (incident == null) {			
			throw new IllegalStateException("Exception 0x156DDE");
		}
		model.addAttribute("incident", incident);
		return "incident/details";
	}
	
	@RequestMapping(value = "search")
	public String search(@RequestParam String q) {
		Long incidentId = Long.valueOf(q);
		return "forward:/incident/" + incidentId;
	}
	
	@RequestMapping("/{incidentId}/audit/create")	
	public String createAudit(User user, @PathVariable Long incidentId, Model model) {
		Incident incident = getIncident(user, incidentId);
		if (incident == null) {
			throw new IllegalStateException("Why, Leo, why?");
		}
		model.addAttribute(auditFormFactory.createAuditForm(user, incident));
		return "incident/audit/create";
	}

	@RequestMapping(value = "/{incidentId}/audit/create", method = RequestMethod.POST)
	public String createAudit(User user, @PathVariable Long incidentId, @Valid @ModelAttribute AuditForm auditForm, Errors errors, RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {
			MessageHelper.addErrorAttribute(model, "incident.audit.create.failed");
			return null;
		}
		Incident incident = getIncident(user, incidentId);
		Audit audit = incidentService.addAudit(user, incident, auditForm);
		MessageHelper.addErrorAttribute(ra, "incident.audit.create.success", audit.getId());
		return "redirect:/incident/" + incidentId;
	}
	
	//
	// private helpers
	//
	
	private Incident getIncident(User user, Long incidentId) {
		return incidentService.getIncident(user, incidentId);
	}
}
