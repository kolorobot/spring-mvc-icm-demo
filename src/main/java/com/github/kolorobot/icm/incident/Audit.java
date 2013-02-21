package com.github.kolorobot.icm.incident;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.github.kolorobot.icm.account.Account;

@Entity
@Table(name = "audit")
public class Audit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "incident_id")
	private Incident incident;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "creator_id", nullable = false, updatable = false)
	private Account creator;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date created;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Incident.Status status;
		
	@Column(name = "previous_status")
	private Incident.Status previousStatus;

	@NotNull
	@Column(name = "operator_id")
	private String operatorId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Incident.Status getStatus() {
		return status;
	}

	public void setStatus(Incident.Status status) {
		this.status = status;
	}

	public Account getCreator() {
		return creator;
	}

	public void setCreator(Account creator) {
		this.creator = creator;
	}

	public Incident.Status getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Incident.Status previousStatus) {
		this.previousStatus = previousStatus;
	}
	
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
	@Override
	public String toString() {
		return "Audit [id=" + id + ", description=" + description
				+ ", created=" + created + "]";
	}
}
