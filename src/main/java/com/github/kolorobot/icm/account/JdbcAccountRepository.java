package com.github.kolorobot.icm.account;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
class JdbcAccountRepository implements AccountRepository {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("select * from account where account.email = ?", new Object[] {email} , new AccountMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = jdbcTemplate.query("select * from account", new AccountMapper());
        return accounts;
    }

    @Override
    public Account findOne(Long accountId) {
        try {
            return jdbcTemplate.queryForObject("select * from account where account.id = ?", new Object[] {accountId} , new AccountMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void save(Account account) {
        jdbcTemplate.update("insert into account (id, name, email, phone, password, role) values (?, ?, ?, ?, ?, ?)",
                new Object[] {null, account.getName(), account.getEmail(), account.getPhone(), account.getPassword(), account.getRole()});
    }

    private static class AccountMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getLong("id"));
            account.setEmail(resultSet.getString("email"));
            account.setPassword(resultSet.getString("password"));
            account.setName(resultSet.getString("name"));
            account.setPhone(resultSet.getString("phone"));
            account.setRole(resultSet.getString("role"));
            return account;
        }
    }
}
