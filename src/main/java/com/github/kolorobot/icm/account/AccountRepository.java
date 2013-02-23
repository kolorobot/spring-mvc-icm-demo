package com.github.kolorobot.icm.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
	@Query(value = "SELECT a FROM Account a WHERE a.email = ?1")
	Account findByEmail(String email);

	@Query(value = "SELECT a FROM Account a WHERE a.operatorId = ?1")
	List<Account> findAll(String operatorId);
}
