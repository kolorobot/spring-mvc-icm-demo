package com.github.kolorobot.icm.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByEmail(String email);
}
