package com.github.kolorobot.icm.incident;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Audit {

	@XmlAttribute
	private Long id;

	@XmlAttribute
	private Long incidentId;

	private Long creatorId;

	private Date created;

	@XmlElement(nillable = true)
	private String description;
	
	private Incident.Status status;

	@XmlElement(nillable = true)
	private Incident.Status previousStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(long incidentId) {
		this.incidentId = incidentId;
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

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public Incident.Status getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Incident.Status previousStatus) {
		this.previousStatus = previousStatus;
	}
	
	@Override
	public String toString() {
		return "Audit [id=" + id + ", description=" + description
				+ ", created=" + created + "]";
	}
}
