package com.github.kolorobot.icm.account;

import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcAccountRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Inject
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean hasEmail(String email) {
        String sql = "select count(*) as result from account where account.email = ?";
        LOGGER.debug("Running SQL query: " + sql);
        return jdbcTemplate.queryForObject(sql, new Object[] {email} , Integer.class) == 0 ? false : true;
    }

    @Override
    public Account findByEmail(String email) {
        try {
            String sql = "select * from account where account.email = ?";
            LOGGER.debug("Running SQL query: " + sql);
            return jdbcTemplate.queryForObject(sql, new Object[] {email} , new AccountMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account order by role";
        LOGGER.debug("Running SQL query: " + sql);
        List<Account> accounts = jdbcTemplate.query(sql, new AccountMapper());
        return accounts == null ? Lists.<Account>newArrayList() : accounts;
    }

    @Override
    public List<Account> findAllByRole(String role) {
        String sql = "select * from account where role = ?";
        LOGGER.debug("Running SQL query: " + sql);
        List<Account> accounts = jdbcTemplate.query(sql, new Object[]{role}, new AccountMapper());
        return accounts == null ? Lists.<Account>newArrayList() : accounts;
    }

    @Override
    public Account findOne(Long accountId) {
        try {
            String sql = "select * from account where account.id = ?";
            LOGGER.debug("Running SQL query: " + sql);
            return jdbcTemplate.queryForObject(sql, new Object[] {accountId} , new AccountMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void save(Account account) {
        long id = jdbcTemplate.queryForObject("select max(id) + 1 from account", Long.class);
        String sql = "insert into account (id, name, email, phone, password, role) values (?, ?, ?, ?, ?, ?)";
        LOGGER.debug("Running SQL query: " + sql);
        jdbcTemplate.update(sql,
                new Object[] {id, account.getName(), account.getEmail(), account.getPhone(), account.getPassword(), account.getRole()});
        account.setId(id);
    }

    @Override
    public void delete(Long accountId) {
        jdbcTemplate.update("delete from account where id = ?", accountId);
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
