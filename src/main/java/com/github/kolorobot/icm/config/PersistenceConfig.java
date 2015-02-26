package com.github.kolorobot.icm.config;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

@Configuration
@Profile("!test")
public class PersistenceConfig  {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() throws SQLException {
        String dataSourcePopulate = System.getProperty("dataSource.populate");
        if (Strings.isNullOrEmpty(dataSourcePopulate) || !dataSourcePopulate.equals("true")) {
            LOGGER.warn("No database schema and initial data created.");
            LOGGER.warn("Please add 'dataSource.populate=true' system property and run the application again.");
            return;
        }
        LOGGER.debug("Populating a database ...");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sqlite/sqlite-schema.sql"));
        databasePopulator.addScript(new ClassPathResource("sqlite/sqlite-accounts.sql"));
        databasePopulator.addScript(new ClassPathResource("sqlite/sqlite-incidents.sql"));
        databasePopulator.populate(dataSource.getConnection());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.sqlite.JDBC.class.getCanonicalName());
        dataSource.setUrl(resolveUrl());
        return dataSource;
    }

    private String resolveUrl() {
        String dataSourceUrl = System.getProperty("dataSource.url");
        if (!Strings.isNullOrEmpty(dataSourceUrl)) {
            return dataSourceUrl;
        }
        String databaseUrl = System.getProperty("user.home") + "/icm.db";
        LOGGER.warn("By default, the database will be created in a user's home directory (" + new File(databaseUrl).getAbsolutePath() + ")");
        LOGGER.warn("If you want to change the data source url, please add 'dataSource.url=<url>' system property and run the application again.");
        return "jdbc:sqlite:" + databaseUrl;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
