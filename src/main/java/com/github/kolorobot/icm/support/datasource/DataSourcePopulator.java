package com.github.kolorobot.icm.support.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataSourcePopulator {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataSourcePopulator.class);

    @Inject
    private DataSource dataSource;

    public boolean isDataSourceSetup() {
        try {
            Statement statement = dataSource.getConnection().createStatement();
            statement.execute("select count(*) from account");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void execute() {
        LOGGER.debug("Populating a database ...");
        resourceDatabasePopulator().execute(dataSource);
    }

    private ResourceDatabasePopulator resourceDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sqlite/sqlite-schema.sql"));
        databasePopulator.addScript(new ClassPathResource("sqlite/sqlite-accounts.sql"));
        databasePopulator.addScript(new ClassPathResource("sqlite/sqlite-incidents.sql"));
        return databasePopulator;
    }
}
