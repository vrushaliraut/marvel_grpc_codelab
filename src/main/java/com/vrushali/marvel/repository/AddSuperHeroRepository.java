package com.vrushali.marvel.repository;

import com.vrushali.marvel.model.AddSuperHero;
import org.skife.jdbi.v2.DBI;

import java.util.List;

public class AddSuperHeroRepository {
    DBI dbi;
    private int queryTimeOutInSeconds;
    private static final String INSERT_INTO_SUPERHERO = "INSERT INTO superhero (message) VALUES (:message)";
    private static final String SELECT_QUERY = "SELECT * FROM superhero";

    public AddSuperHeroRepository(DBI dbi, int queryTimeOutInSeconds) {
        this.dbi = dbi;
        this.queryTimeOutInSeconds = queryTimeOutInSeconds;
    }

    public void addSuperhero(AddSuperHero addSuperHeroMessage) {
        dbi.withHandle(handle -> {
            handle.createStatement(INSERT_INTO_SUPERHERO)
                    .bind("message", addSuperHeroMessage.getMessage())
                    .execute();
            return null;
        });
    }

    List<AddSuperHero> find() {
        return dbi.withHandle(handle -> handle.createQuery(SELECT_QUERY)
                .map(new AddSuperheroResultSetMapper())
                .setQueryTimeout(queryTimeOutInSeconds)
                .list()
        );
    }
}
