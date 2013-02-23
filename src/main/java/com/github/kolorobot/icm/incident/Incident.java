package com.github.kolorobot.icm.incident;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.Address;

@Entity
@Table(name = "incident")
public class Incident {
	
	public enum Status {
		NEW, CONFIRMED, NOT_CONFIRMED, SOLVED, CLOSED;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "creator_id", nullable = false, updatable = false)
	private Account creator;
	
	@Column(name = "incident_type")
	@NotBlank
	private String incidentType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@NotBlank
	private String description;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	private Status status = Status.NEW;
	
	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private Account assignee;
	
	@OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy(value = "created DESC")
	private List<Audit> audits;
	
	@NotNull
	@Column(name = "operator_id")
	private String operatorId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getCreator() {
		return creator;
	}

	public void setCreator(Account creator) {
		this.creator = creator;
	}

	public String getIncidentType() {
		return incidentType;
	}

	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
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
		if (audits == null) {
			audits = new ArrayList<Audit>();
		}
		return audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	
}
