package com.github.kolorobot.icm.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
class AccountController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountRepository userRepository;
	
	@RequestMapping(value = "account/current", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Account accounts(UserDetails userDetails) {
		LOG.info(userDetails.toString());
		return userRepository.findByEmail(userDetails.getUsername());
	}
}
