package com.github.kolorobot.icm.incident;

import java.util.List;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.incident.Incident.Status;

class AuditForm {
	
	private Incident.Status oldStatus;
	private Incident.Status newStatus;
	private String description;
	private Long assigneeId;
	private List<Incident.Status> availableStatuses;
	private List<Account> availableEmployees;

	public void setAvailableStatuses(List<Incident.Status> availableStatuses) {
		this.availableStatuses = availableStatuses;
	}

	public void setNewStatus(Status newStatus) {
		this.newStatus = newStatus;
	}

	public Status getNewStatus() {
		return newStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Incident.Status> getAvailableStatuses() {
		return availableStatuses;
	}
	
	public boolean canChangeStatus() {
		if (availableStatuses == null) {
			return false;
		}
		return availableStatuses.size() > 0;
	}

	public Incident.Status getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Incident.Status oldStatus) {
		this.oldStatus = oldStatus;
	}

	public boolean canAssignEmployee() {
		if (availableEmployees == null) {
			return false;
		}
		return availableEmployees.size() > 0;
	}

	public List<Account> getAvailableEmployees() {
		return availableEmployees;
	}

	public void setAvailableEmployees(List<Account> availableEmployees) {
		this.availableEmployees = availableEmployees;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

}
