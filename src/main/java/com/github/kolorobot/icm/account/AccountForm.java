package com.github.kolorobot.icm.account;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

// @UniqueEmail
public class AccountForm {

	@NotBlank
	@Size(max = 49)
	private String name;
	@NotBlank
	@Email
	private String email;

	private String phone;
	@NotBlank
	@Size(max = 50)
    private String password;

    @NotBlank
    @Size(max = 50)
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

    // @AssertTrue(message =  "{com.github.kolorobot.icm.PasswordsMatch}")
    public boolean isPasswordsMatch() {
        if (getPassword() == null) {
            return false;
        }
        return getPassword().equals(getConfirmedPassword());
    }

    public String getPhone() {
        if (phone != null && phone.isEmpty()) {
            return null;
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public Account createUserAccount() {
		return createAccount("ROLE_USER");
	}

    public Account createEmployeeAccount() {
        return createAccount("ROLE_EMPLOYEE");
    }

    private Account createAccount(String role) {
        Account account = new Account(getName(), getEmail(), getPassword(), role);
        account.setPhone(getPhone());
        return account;
    }
}
