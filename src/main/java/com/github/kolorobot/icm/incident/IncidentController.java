package com.github.kolorobot.icm.incident;

import java.util.ArrayList;
import java.util.Date;
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

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.AccountRepository;
import com.github.kolorobot.icm.account.Address;
import com.github.kolorobot.icm.support.web.Message;

@Controller
@RequestMapping("/incident")
class IncidentController {
	
	@Inject
	private AccountRepository accountRepository;
	
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
		
		Address incidentAddress = new Address();
		incidentAddress.setCityLine(incidentForm.getCityLine());
		incidentAddress.setAddressLine(incidentForm.getAddressLine());
		
		Incident incident = new Incident();
		incident.setAddress(incidentAddress);
		incident.setDescription(incidentForm.getDescription());
		incident.setIncidentType(incidentForm.getType());
		Account reporter = accountRepository.findByEmail(user.getUsername());
		Account assignee = accountRepository.findByEmail("admin@icm.com");
		incident.setCreator(reporter);
		incident.setAssignee(assignee);
		incident.setCreated(new Date());
		incidentRepository.save(incident);
		
		ra.addFlashAttribute(Message.MESSAGE_ATTRIBUTE, new Message("New incident created successfully", Message.Type.SUCCESS));
		
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
	
	// FIXME Should be available only for ROLE_ADMIN
	@RequestMapping("/{incidentId}/audit/create")	
	public String createAudit(@PathVariable Long incidentId, Model model) {
		model.addAttribute(new AuditForm());
		return "incident/audit/create";
	}
	
	// FIXME Should be available only for ROLE_ADMIN
	@RequestMapping(value = "/{incidentId}/audit/create", method = RequestMethod.POST)
	@Transactional
	public String createAudit(UserDetails user, @PathVariable Long incidentId, @Valid @ModelAttribute AuditForm auditForm, Errors errors, RedirectAttributes ra) {
		// FIXME Only current user's incident
		Audit audit = new Audit();
		audit.setDescription(auditForm.getDescription());		
		audit.setStatus(auditForm.getStatus());
		Account creator = accountRepository.findByEmail(user.getUsername());
		Incident incident = incidentRepository.findOne(incidentId);
		audit.setCreated(new Date());
		audit.setCreator(creator);
		incident.addAudit(audit);
		incidentRepository.save(incident);
		
		ra.addFlashAttribute(Message.MESSAGE_ATTRIBUTE, new Message("New audit created successfully", Message.Type.SUCCESS));
		
		return "redirect:/incident/" + incidentId;
	}
}
