package com.github.kolorobot.icm.incident;

import static com.github.kolorobot.icm.account.Account.*;
import static com.github.kolorobot.icm.incident.Incident.Status.*;

import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.AccountRepository;
import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.incident.Incident.Status;
import com.google.common.collect.Lists;

@Component
class AuditFormFactory {
	
	@Inject
	private AccountRepository accountRepository;
	
	public AuditForm createAuditForm(User user, Incident incident) {
		AuditForm form = new AuditForm();
		form.setOldStatus(incident.getStatus());
		form.setAvailableStatuses(getAvailableStatuses(user, incident));
		form.setAvailableEmployees(getAvailableEmployees(user, incident));
		return form;
	}
	
	private List<Status> getAvailableStatuses(User user, Incident incident) {
		Status status = incident.getStatus();
		if (user.isInRole(ROLE_ADMIN)) {
			if(EnumSet.of(NOT_CONFIRMED, SOLVED).contains(status)) {
				return Lists.newArrayList(CLOSED, NEW);
			}
		} else if (user.isInRole(ROLE_EMPLOYEE)) {
			switch(status) {
				case NEW:
					return Lists.newArrayList(CONFIRMED, NOT_CONFIRMED, SOLVED);
				case CONFIRMED:
					return Lists.newArrayList(NEW, NOT_CONFIRMED, SOLVED);
				case NOT_CONFIRMED:
					return Lists.newArrayList(NEW, CONFIRMED, SOLVED);
				case SOLVED:
					return Lists.newArrayList(NEW, CONFIRMED, NOT_CONFIRMED);
			default:
				break;
			}
		} else {
			throw new IllegalAccessError();
		}
		return null;
	}
	
	private List<Account> getAvailableEmployees(User user, Incident incident) {
		if (user.isInRole(ROLE_ADMIN) 
				&& EnumSet.of(NEW, NOT_CONFIRMED, SOLVED).contains(incident.getStatus())) {
			return accountRepository.findAll();
		}
		return null;
	}
}
