package com.github.kolorobot.icm.incident;

import com.github.kolorobot.icm.config.TestDataSourceConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataSourceConfig.class)
public class JdbcIncidentRepositoryTest  {

    private JdbcIncidentRepository jdbcIncidentRepository;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Before
    public void configure() throws Exception {
        jdbcIncidentRepository = new JdbcIncidentRepository(jdbcTemplate);
    }

    @Test
    public void findOne() throws SQLException {
        Incident incident = jdbcIncidentRepository.findOne(1l);
        Assert.assertNotNull(incident);
    }

    @Test
    public void findOneByIdAndAssigneeIdOrCreatorId() {
        Incident incident = jdbcIncidentRepository.findOneByIdAndAssigneeIdOrCreatorId(3l, 2l);
        Assert.assertNotNull(incident);
    }

    @Test
    public void findAll() {
        List<Incident> incidents = jdbcIncidentRepository.findAll();
        Assert.assertFalse(incidents.isEmpty());
    }
}
