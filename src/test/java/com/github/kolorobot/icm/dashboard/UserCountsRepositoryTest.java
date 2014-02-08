package com.github.kolorobot.icm.dashboard;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.config.TestDataSourceConfig;
import org.fest.assertions.MapAssert;
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
public class UserCountsRepositoryTest {

    private UserCountsRepository userCountsRepository;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        userCountsRepository = new UserCountsRepository(jdbcTemplate);
    }

    @Test
    public void userCounts() {
        UserCounts result = userCountsRepository.userCounts();
        assertThat(result.all()).as("Count all").isEqualTo(5);
        assertThat(result.byRole()).as("Count by role")
                .includes(MapAssert.entry(Account.ROLE_USER, 3))
                .includes(MapAssert.entry(Account.ROLE_ADMIN, 1))
                .includes(MapAssert.entry(Account.ROLE_EMPLOYEE, 1));
    }
}
