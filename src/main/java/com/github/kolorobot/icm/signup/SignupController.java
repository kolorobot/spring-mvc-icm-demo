package com.github.kolorobot.icm.signup;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.UserService;
import com.github.kolorobot.icm.support.thymeleaf.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Layout("layouts/blank")
class SignupController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
        model.addAttribute(new SignupForm());
        return "signup/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {
		if (errors.hasErrors()) {
            return "signup/signup";
		}
		Account account = signupForm.createUserAccount();
		userService.createAccount(account);
		userService.signin(account);
		
		return "redirect:/";
	}
}
