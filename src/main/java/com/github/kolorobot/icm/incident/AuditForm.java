package com.github.kolorobot.icm.incident;

import java.util.Arrays;
import java.util.List;

import com.github.kolorobot.icm.incident.Incident.Status;

public class AuditForm {
	private Incident.Status status;
	private String description;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Incident.Status> getAvailableStatuses() {
		return Arrays.asList(Status.values());
	}
}
