package com.github.kolorobot.icm.home;

import com.github.kolorobot.icm.support.datasource.DataSourcePopulator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.security.Principal;

@Controller
class HomeController {

    @ModelAttribute("page")
    public String module() {
        return "home";
    }

    @Inject
    private DataSourcePopulator dataSourcePopulator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return principal != null ? "forward:/incident/list" : "home/homeNotSignedIn";
	}

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setupView() {
        return "home/setup";
    }

    @RequestMapping(value = "/setup", method = RequestMethod.GET, params = "action=do")
    public String setup(Principal principal) {
        dataSourcePopulator.execute();
        return principal != null ? "redirect:/logout" : "redirect:/signin";
    }
}
