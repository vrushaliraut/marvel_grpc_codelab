package com.vrushali.marvel.repository;


import com.vrushali.marvel.model.Superhero;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarvelSuperheroResultSetMapper implements ResultSetMapper<Superhero> {

    public static final String SUPERHERO_NAME = "superhero_name";

    @Override
    public Superhero map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Superhero superhero = new Superhero(r.getString(SUPERHERO_NAME));
        return superhero;
    }
}
