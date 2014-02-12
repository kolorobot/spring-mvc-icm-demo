package com.github.kolorobot.icm.dashboard;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.security.Principal;

@Controller
@RequestMapping("dashboard")
class DashboardController {

    @Inject
    private IncidentCountsRepository incidentCountsRepository;

    @Inject
    private UserCountsRepository userCountsRepository;

    @ModelAttribute("page")
    public String module() {
        return "dashboard";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("incidentCounts", incidentCountsRepository.incidentCounts());
        model.addAttribute("auditCounts", incidentCountsRepository.auditCounts());
        model.addAttribute("userCounts", userCountsRepository.userCounts());
        return "dashboard/dashboard";
    }
}
