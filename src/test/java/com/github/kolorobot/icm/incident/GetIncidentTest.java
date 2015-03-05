package com.github.kolorobot.icm.incident;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.User;

@RunWith(MockitoJUnitRunner.class)
public class GetIncidentTest {
	
	private IncidentService service;
	
	@Mock
	private IncidentRepository incidentRepository ;

    @Before
    public void setUp() throws Exception {
        service = new IncidentService(incidentRepository, null, null, null);
    }

    @Test
	public void shouldFindIncidentByIdAndCreatorIdWhenUserIsInRoleUser() {
		// arrange
		Account account = createAccount(Account.ROLE_USER);
		User user = new User(account);
		// act
		service.getIncident(user, 1L);
		// assert
		verify(incidentRepository, times(1)).findOneByIdAndCreatorId(1L, 1L);
	}
	
	@Test
	public void shouldFindIncidentByIdAndAssigneeIdOrCreatorIdWhenUserIsInRoleEmployee() {
		// arrange
		Account account = createAccount(Account.ROLE_EMPLOYEE);
		User user = new User(account);
		// act
		service.getIncident(user, 1L);
		// assert
		verify(incidentRepository, times(1)).findOneByIdAndAssigneeIdOrCreatorId(1L, 1L);
	}
	
	@Test
	public void shouldFindIncidentByIdWhenUserIsInRoleAdmin() {
		// arrange
		Account account = createAccount(Account.ROLE_ADMIN);
		User user = new User(account);
		// act
		service.getIncident(user, 1L);
		// assert
		verify(incidentRepository, times(1)).findOne(1L);
	}

	private Account createAccount(String role) {
		Account account = new Account("name", "email", "password", role);
		account.setId(1L);
		return account;
	}

}
