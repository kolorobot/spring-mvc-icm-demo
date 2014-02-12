package com.github.kolorobot.icm.incident;

import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

public class Incident {
	
	public enum Status {
		NEW, CONFIRMED, NOT_CONFIRMED, SOLVED, CLOSED;
        public static Status valueOf(int ordinal) {
            for(Status status : Status.values()) {
                if (status.ordinal() == ordinal) {
                    return status;
                }
            }
            return null;
        }
    }

	private Long id;
	
	private Long creatorId;
	
	@NotBlank
	@Size(max = 50)
	private String incidentType;
	
	private Address address;
	
	@NotBlank
	@Size(max = 255)
	private String description;
		
	private Date created;
	
	private Status status = Status.NEW;
	
	private Long assigneeId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
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


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("creatorId", creatorId)
                .add("incidentType", incidentType)
                .add("created", created)
                .add("status", status)
                .toString();
    }
}
