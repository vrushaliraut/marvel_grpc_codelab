package com.vrushali.marvel.repository;

import com.vrushali.marvel.model.Superhero;
import org.skife.jdbi.v2.DBI;

import java.util.List;

public class MarvelSuperheroRepository {
    public static final String SUPERHERO_NAME = "superhero_name";
    DBI dbi;
    private int queryTimeOutInSeconds;
    private static final String INSERT_INTO_MARVEL = "INSERT INTO superhero (superhero_name) VALUES (:superhero_name)";
    private static final String SELECT_QUERY = "SELECT * FROM superhero";

    public MarvelSuperheroRepository(DBI dbi, int queryTimeOutInSeconds) {
        this.dbi = dbi;
        this.queryTimeOutInSeconds = queryTimeOutInSeconds;
    }

    public void saveMarvelSuperheroes(Superhero marvelSuperhero) {
        dbi.withHandle(handle -> {
            handle.createStatement(INSERT_INTO_MARVEL)
                    .bind(SUPERHERO_NAME, marvelSuperhero.getSuperheroname())
                    .execute();
            return null;
        });
    }

    public List<Superhero> find() {
        return dbi.withHandle(handle -> handle.createQuery(SELECT_QUERY)
                .map(new MarvelSuperheroResultSetMapper())
                .setQueryTimeout(queryTimeOutInSeconds)
                .list()
        );
    }
}
