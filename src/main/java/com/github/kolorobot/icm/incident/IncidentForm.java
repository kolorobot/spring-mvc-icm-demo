package com.github.kolorobot.icm.incident;

import org.hibernate.validator.constraints.NotBlank;

class IncidentForm {
	
	@NotBlank
	private String description;
	@NotBlank
	private String type;
	@NotBlank
	private String addressLine;
	@NotBlank
	private String cityLine;

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

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCityLine() {
		return cityLine;
	}

	public void setCityLine(String cityLine) {
		this.cityLine = cityLine;
	}
}
