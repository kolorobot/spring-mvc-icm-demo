package com.github.kolorobot.icm.home;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class HomeController {

    @ModelAttribute("page")
    public String module() {
        return "home";
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return principal != null ? "redirect:/incident/list" : "home/homeNotSignedIn";
	}
	
}
