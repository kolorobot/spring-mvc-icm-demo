package com.github.kolorobot.icm.incident;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import com.github.kolorobot.icm.account.Account;

@Entity(name = "incident")
public class Incident {
	
	public enum Status {
		NEW, CONFIRMED, NOT_CONFIRMED, IN_PROGRESS, DONE;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "reporter_id", nullable = false, updatable = false)
	private Account reporter;
	
	@Column(name = "incident_type")
	@NotBlank
	private String incidentType;
	
	@Column(name = "incident_address")
	@NotBlank
	private String incidentAddress;
	
	@NotBlank
	private String description;
	
	private Status status = Status.NEW;
	
	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private Account assignee;
	
	@OneToMany(mappedBy = "incident")
	private List<Audit> audits;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getReporter() {
		return reporter;
	}

	public void setReporter(Account reporter) {
		this.reporter = reporter;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}

	public String getIncidentAddress() {
		return incidentAddress;
	}

	public void setIncidentAddress(String incidentAddress) {
		this.incidentAddress = incidentAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Account getAssignee() {
		return assignee;
	}

	public void setAssignee(Account assignee) {
		this.assignee = assignee;
	}

	public List<Audit> getAudits() {
		return audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}
	
}
