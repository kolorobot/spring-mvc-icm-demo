package com.github.kolorobot.icm.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class Account implements java.io.Serializable {
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

	@XmlAttribute
	private Long id;
	
	@NotBlank
	@Size(max = 49)
	private String name;

	@Size(max = 50)
	private String email;
	
	private String phone;
	
	@JsonIgnore
	@XmlTransient
	private String password;

	private String role = ROLE_USER;
	
	protected Account() {

	}
	
	public Account(String name, String email, String password, String role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User asUser() {
		return new User(this);
	}
}
