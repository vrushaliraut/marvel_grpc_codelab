package com.vrushali.marvel.repository;


import com.vrushali.marvel.model.AddSuperHero;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSuperheroResultSetMapper implements ResultSetMapper<AddSuperHero> {

    @Override
    public AddSuperHero map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        AddSuperHero message = new AddSuperHero(r.getString("message"));
        return message;
    }
}