package com.vrushali.marvel.repository;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.vrushali.marvel.factory.DBIFactory;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.DBI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DBSetup {
    private static DBI dbi;

    static {
        if(dbi == null) {
            initializeDB();
        }
    }

    private static void initializeDB() {
        final List<String> requiredFields =
                Arrays.asList("DB_USERNAME", "DB_PASSWORD", "DB_HOST", "DB_PORT", "DB_NAME");
        final ApplicationConfiguration appConfig = Figaro.configure(new HashSet<>(requiredFields));
        final DBIFactory dbiFactory = new DBIFactory(appConfig);
        dbi = dbiFactory.create();
        runFlywayMigration(appConfig, dbiFactory);
    }

    private static void runFlywayMigration(ApplicationConfiguration appConfig, DBIFactory dbiFactory) {
        final Flyway flyway = new Flyway();
        String url = dbiFactory.getDatabaseConnectionUrl();
        final String username = appConfig.getValueAsString("DB_USERNAME");
        final String password = appConfig.getValueAsString("DB_PASSWORD");
        flyway.setDataSource(url, username, password);
        flyway.migrate();
    }

    public static DBI getDBI() {
        return dbi;
    }


}
