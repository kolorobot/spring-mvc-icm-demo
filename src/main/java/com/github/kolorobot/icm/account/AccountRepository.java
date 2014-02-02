package com.github.kolorobot.icm.account;

import java.util.List;

public interface AccountRepository {

	Account findByEmail(String email);

	List<Account> findAll();

    Account findOne(Long accountId);

    void save(Account account);
}
