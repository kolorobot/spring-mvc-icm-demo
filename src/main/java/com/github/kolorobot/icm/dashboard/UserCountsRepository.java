package com.github.kolorobot.icm.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
class UserCountsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentCountsRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Inject
    UserCountsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    UserCounts userCounts() {
        UserCounts.Builder builder = new UserCounts.Builder();
        countAll(builder);
        countByRole(builder);
        return builder.build();
    }

    private void countByRole(final UserCounts.Builder builder) {
        String sql = "select count(account.role) as result, user_roles.value as role from user_roles " +
                "left outer join account on account.role = user_roles.value group by user_roles.value";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                builder.addRole(rs.getString("role"), rs.getInt("result"));
            }
        });
    }

    private void countAll(UserCounts.Builder builder) {
        builder.setAll(
                jdbcTemplate.queryForObject("select count(id) from account", Integer.class)
        );
    }
}
