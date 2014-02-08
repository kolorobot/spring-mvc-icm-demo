package com.github.kolorobot.icm.dashboard;

import com.github.kolorobot.icm.incident.Incident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
class IncidentCountsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncidentCountsRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Inject
    IncidentCountsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    IncidentCounts incidentCounts() {
        return counts("incident");
    }

    IncidentCounts auditCounts() {
        return counts("audit");
    }

    private IncidentCounts counts(String table) {
        IncidentCounts.Builder builder = new IncidentCounts.Builder(table);
        countAll(builder, table);
        countCreatedToday(builder, table);
        countByStatus(builder, table);
        return builder.build();
    }


    private void countAll(IncidentCounts.Builder builder, String table) {
        builder.setAll(jdbcTemplate.queryForObject(countQuery(table), Integer.class));
    }

    private void countCreatedToday(IncidentCounts.Builder builder, String table) {
        builder.setCreatedToday(jdbcTemplate.queryForObject(countQuery(table)
                .concat(" where created >= date('now')"), Integer.class));
    }

    private void countByStatus(final IncidentCounts.Builder builder, String table) {
        String query = "select count(id) as result, status.value, status.ordinal as status from status " +
                "left outer join " + table + " on " + table + ".status = status.ordinal group by status.ordinal order by status.ordinal asc";

        jdbcTemplate.query(query, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                builder.addStatus(
                        Incident.Status.valueOf(rs.getInt("status")), rs.getInt("result")
                );
            }
        });
    }

    private String countQuery(String table) {
        return "select count(id) from " + table;
    }

}
