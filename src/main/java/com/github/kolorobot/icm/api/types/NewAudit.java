package com.github.kolorobot.icm.api.types;

import com.github.kolorobot.icm.incident.Incident;

import javax.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NewAudit {

    @XmlAttribute(required = true)
    private Long userId;
    @XmlAttribute(required = true)
    private Long incidentId;

    @XmlElement
    private Incident.Status newStatus;
    @XmlElement
    private String description;
    @XmlElement(required = true)
    private Long assigneeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public Incident.Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Incident.Status newStatus) {
        this.newStatus = newStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
}
