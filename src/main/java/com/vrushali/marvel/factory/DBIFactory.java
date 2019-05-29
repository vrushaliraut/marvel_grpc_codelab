package com.vrushali.marvel.factory;

import com.gojek.ApplicationConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.skife.jdbi.v2.DBI;

public class DBIFactory {
    private static HikariDataSource dataSource = null;

    private final ApplicationConfiguration appConfig;

    public DBIFactory(ApplicationConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    public DBI create() {
        HikariDataSource dataSource = getDataSource();
        return new DBI(dataSource);
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    private synchronized HikariDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }
        return dataSource;
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(getDatabaseConnectionUrl());
        config.setLeakDetectionThreshold(appConfig.getValueAsInt("LEAK_THRESHOLD"));
        config.setUsername(appConfig.getValueAsString("DB_USERNAME"));
        config.setPassword(appConfig.getValueAsString("DB_PASSWORD"));
        config.setMaximumPoolSize(appConfig.getValueAsInt("DB_MAX_POOL_SIZE"));
        return new HikariDataSource(config);
    }

    public String getDatabaseConnectionUrl() {
        final String dbName = appConfig.getValueAsString("DB_NAME");
        final String dbPort = appConfig.getValueAsString("DB_PORT");
        final String dbHost = appConfig.getValueAsString("DB_HOST");
        return String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);
    }
}
