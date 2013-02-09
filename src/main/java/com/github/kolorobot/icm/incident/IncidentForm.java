package com.github.kolorobot.icm.incident;

import org.hibernate.validator.constraints.NotBlank;

class IncidentForm {
	@NotBlank
	private String description;
	@NotBlank
	private String type;
	@NotBlank
	private String address;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
