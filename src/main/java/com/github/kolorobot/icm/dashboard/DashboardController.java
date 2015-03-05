package com.github.kolorobot.icm.dashboard;

import com.github.kolorobot.icm.support.datasource.DataSourcePopulator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("dashboard")
class DashboardController {

    @Inject
    private IncidentCountsRepository incidentCountsRepository;

    @Inject
    private UserCountsRepository userCountsRepository;

    @Inject
    private DataSourcePopulator dataSourcePopulator;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"reset"}, method = RequestMethod.GET)
    public String reset() {
        dataSourcePopulator.execute();
        return "forward:/logout";
    }
}
