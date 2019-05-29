package com.vrushali.marvel.repository;

import com.vrushali.marvel.model.Superhero;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MarvelSuperheroRepositoryTest extends DataStoreRepositoryTest {
    private MarvelSuperheroRepository superheroRepository;

    @Before
    public void setUp() throws Exception {
        superheroRepository = new MarvelSuperheroRepository(dbi, 4);
    }

    @Test
    public void shouldInsertToDB() {
        Superhero superhero = new Superhero("Thor");

        superheroRepository.saveMarvelSuperheroes(superhero);

        List<Superhero> marvelSuperheroes = superheroRepository.find();
        assertEquals(1, marvelSuperheroes.size());
        String superheroName = marvelSuperheroes.get(0).getSuperheroname();
        assertEquals("Thor", superheroName);
    }
}
