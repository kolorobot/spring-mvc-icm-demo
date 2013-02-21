package com.github.kolorobot.icm.home;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.kolorobot.icm.support.web.Message;

@Controller
class HomeController {
	
	@RequestMapping(value = "/operator", method = RequestMethod.GET)
	public void entrance() {
		
	}
	
	@RequestMapping(value = "/operator", method = RequestMethod.POST)
	public String entrance(@RequestParam String id, HttpSession session, Model model) {
		if (!StringUtils.hasLength(id) || id.length() > 3) {
			model.addAttribute(Message.MESSAGE_ATTRIBUTE, new Message("opertor.id.invalidFormat", Message.Type.ERROR));
			return null;			
		}
		session.setAttribute("operatorId", id);		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return principal != null ? "redirect:/incident/list" : "homeNotSignedIn";
	}
	
}
