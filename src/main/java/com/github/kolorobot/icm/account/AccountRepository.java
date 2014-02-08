package com.github.kolorobot.icm.account;

import java.util.List;

public interface AccountRepository {

	Account findByEmail(String email);

	List<Account> findAll();

    List<Account> findAllByRole(String role);

    Account findOne(Long accountId);

    void save(Account account);

    void delete(Long accountId);
}
