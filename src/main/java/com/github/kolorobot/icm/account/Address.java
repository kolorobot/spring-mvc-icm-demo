package com.github.kolorobot.icm.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity(name = "address")
public class Address implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "address_line")
	@Size(max = 255)
	private String addressLine;
	
	@Column(name = "city_line")
	@Size(max = 255)
	private String cityLine;
	
	@NotNull
	@Column(name = "operator_id")
	private String operatorId;
	
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
}
