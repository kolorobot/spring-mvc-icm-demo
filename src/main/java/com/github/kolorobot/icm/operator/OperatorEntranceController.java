package com.github.kolorobot.icm.operator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.kolorobot.icm.support.web.MessageHelper;

public class OperatorEntranceController {
	
	@RequestMapping(value = "/operator", method = RequestMethod.GET)
	public void entrance() {}
	
	@RequestMapping(value = "/operator", method = RequestMethod.POST)
	public String entrance(@RequestParam String id, Model model, HttpServletResponse response, HttpServletRequest request) {
		if (!StringUtils.hasLength(id) || id.length() > 3) {
			MessageHelper.addErrorAttribute(model, "opertor.id.invalidFormat");
			return null;			
		}
		OperatorHelper.setOperatorCookie(request, response, id);
		return "redirect:/";
	}
}
