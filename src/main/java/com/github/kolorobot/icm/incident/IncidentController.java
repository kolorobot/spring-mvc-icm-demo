package com.github.kolorobot.icm.incident;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.support.web.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/incident")
class IncidentController {

	@Inject
	private IncidentService incidentService;
    @Inject
	private AuditFormFactory auditFormFactory;

    @ModelAttribute("page")
    public String module() {
        return "home";
    }

    @RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute(new IncidentForm());
        return "incident/create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(User user, @Valid @ModelAttribute IncidentForm incidentForm, Errors errors, RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {			
			MessageHelper.addErrorAttribute(model, "incident.create.failed");
            return "incident/create";
		}
		Incident incident = incidentService.create(user, incidentForm);
		MessageHelper.addSuccessAttribute(ra, "incident.create.success", incident.getId());
		return "redirect:/incident/list";
	}	
	
	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false) Incident.Status status, User user, Model model) {
		model.addAttribute("incidents", incidentService.getIncidents(user, status));
        return "incident/list";
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam String q, Model model) {
        model.addAttribute("incidents", incidentService.search(q));
        return "incident/list";
    }
	
	@RequestMapping(value = "/{id}")
	public String details(User user, @PathVariable("id") Long id, Model model) {
		Incident incident = getIncident(user, id);
		if (incident == null) {			
			throw new IllegalStateException("Exception 0x156DDE");
		}
		model.addAttribute("incident", incident);
        model.addAttribute("audits", getAudits(incident));
        model.addAttribute("incidentCreator", getCreator(incident));
        model.addAttribute("incidentAssignee", getAssignee(incident));
		return "incident/details";
	}

	@RequestMapping("/{incidentId}/audit/create")	
	public String createAudit(User user, @PathVariable Long incidentId, Model model) {
		Incident incident = getIncident(user, incidentId);
		if (incident == null) {
			throw new IllegalStateException("Why, Leo, why?");
		}
		model.addAttribute(auditFormFactory.createAuditForm(user, incident));
		return "incident/createAudit";
	}

	@RequestMapping(value = "/{incidentId}/audit/create", method = RequestMethod.POST)
	public String createAudit(User user, @PathVariable Long incidentId, @Valid @ModelAttribute AuditForm auditForm, Errors errors, RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {
			MessageHelper.addErrorAttribute(model, "incident.audit.create.failed");
            return "incident/createAudit";
		}
		Incident incident = getIncident(user, incidentId);
		Audit audit = incidentService.addAudit(user, incident, auditForm);
		MessageHelper.addInfoAttribute(ra, "incident.audit.create.success", audit.getId());
		return "redirect:/incident/" + incidentId;
	}

	//
	// private helpers
	//
	
	private Incident getIncident(User user, Long incidentId) {
		return incidentService.getIncident(user, incidentId);
	}

    private List<Audit> getAudits(Incident incident) {
        return incidentService.getAudits(incident);
    }

    private Account getCreator(Incident incident) {
        return incidentService.getCreator(incident);
    }

    private Account getAssignee(Incident incident) {
        return incidentService.getAssignee(incident);
    }
}
