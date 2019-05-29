package com.vrushali.marvel.flyway;

import org.flywaydb.core.Flyway;

public class Migration {

    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:postgresql://localhost:8080/marvel_superhero", "postgres", "postgres");
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    }
}