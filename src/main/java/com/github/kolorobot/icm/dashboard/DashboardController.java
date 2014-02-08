package com.github.kolorobot.icm.dashboard;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("dashboard")
@PreAuthorize("hasRole('ROLE_ADMIN')")
class DashboardController {

    @ModelAttribute("page")
    public String module() {
        return "dashboard";
    }

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String index() {
        return "dashboard/dashboard";
	}
}
