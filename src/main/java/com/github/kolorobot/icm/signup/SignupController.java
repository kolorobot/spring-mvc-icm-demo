package com.github.kolorobot.icm.signup;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
class SignupController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "signup")
	public SignupForm signup() {
		return new SignupForm();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {
		if (errors.hasErrors()) {
			return null;
		}
		Account account = signupForm.createAccount();
		userService.createAccount(account);
		userService.signin(account);
		
		return "redirect:/";
	}
}
