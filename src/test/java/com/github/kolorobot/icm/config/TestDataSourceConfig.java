package com.github.kolorobot.icm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class TestDataSourceConfig {

    private String driver = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite::memory:";

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() throws SQLException {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new FileSystemResource("src/main/resources/sqlite/sqlite-schema.sql"));
        databasePopulator.addScript(new FileSystemResource("src/main/resources/sqlite/sqlite-accounts.sql"));
        databasePopulator.addScript(new FileSystemResource("src/main/resources/sqlite/sqlite-incidents.sql"));
        databasePopulator.populate(dataSource.getConnection());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
