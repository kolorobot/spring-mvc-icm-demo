package com.github.kolorobot.icm.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "address")
public class Address implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "address_line")
	private String addressLine;
	
	@Column(name = "city_line")
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
