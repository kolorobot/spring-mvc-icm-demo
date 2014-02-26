package com.github.kolorobot.icm.account;

import com.github.kolorobot.icm.config.TestDataSourceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(classes = TestDataSourceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcAccountRepositoryTest {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private JdbcAccountRepository jdbcAccountRepository;

    @Before
    public void setUp() throws Exception {
        jdbcAccountRepository = new JdbcAccountRepository(jdbcTemplate);
    }

    @Test
    public void hasEmail() {
        assertThat(jdbcAccountRepository.hasEmail("not_existing_email")).isFalse();
        assertThat(jdbcAccountRepository.hasEmail("icm-admin@icm.com")).isTrue();
    }
}
