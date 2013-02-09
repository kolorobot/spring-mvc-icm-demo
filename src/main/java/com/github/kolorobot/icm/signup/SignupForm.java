package com.github.kolorobot.icm.signup;

import org.hibernate.validator.constraints.*;

import com.github.kolorobot.icm.account.Account;

class SignupForm {

	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmedPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public Account createAccount() {
		return new Account(getName(), getEmail(), getPassword(), "ROLE_USER");
	}
}
