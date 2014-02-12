package com.github.kolorobot.icm.incident;

import javax.validation.constraints.Size;
import java.io.Serializable;

@SuppressWarnings("serial")
class Address implements Serializable {
	
	private Long id;
	
	@Size(max = 255)
	private String addressLine;
	
	@Size(max = 255)
	private String cityLine;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
