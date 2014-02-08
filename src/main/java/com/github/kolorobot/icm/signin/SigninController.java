package com.github.kolorobot.icm.signin;

import com.github.kolorobot.icm.support.thymeleaf.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Layout("layouts/blank")
class SigninController {

	@RequestMapping(value = "signin")
	public String signin() {
        return "signin/signin";
    }
}
