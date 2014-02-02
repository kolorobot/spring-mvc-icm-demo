package com.github.kolorobot.icm.account;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

@SuppressWarnings("serial")
public class User extends org.springframework.security.core.userdetails.User {

	private final Long accountId;

	public User(Account account) {		
		super(account.getEmail(), account.getPassword(), Collections.singleton(new SimpleGrantedAuthority(account.getRole())));
		this.accountId = account.getId();
	}

	public Long getAccountId() {
		return accountId;
	}
	
	public boolean isInRole(String role) {
		return getAuthorities().contains(new SimpleGrantedAuthority(role));
	}
}
