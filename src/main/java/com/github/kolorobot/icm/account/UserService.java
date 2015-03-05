package com.github.kolorobot.icm.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return toUser(account);
	}

	private UserDetails toUser(Account account) {
		return new com.github.kolorobot.icm.account.User(account);
	}

	public void createAccount(Account account) {
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        accountRepository.save(account);
        // FIXME Plain password should not appear in logs
        LOGGER.info("Created an account: [email={}, password={}]", account.getEmail(), password);
	}
	
	
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(Account account) {
		UserDetails user = toUser(account);
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());		
	}
}
