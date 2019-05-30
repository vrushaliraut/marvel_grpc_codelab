package com.vrushali.marvel.repository;

import com.vrushali.marvel.model.AddSuperHero;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddSuperHeroRepositoryTest extends DataStoreRepositoryTest {
    private AddSuperHeroRepository addSuperHeroRepository;

    @Before
    public void setUp() throws Exception {
        addSuperHeroRepository = new AddSuperHeroRepository(dbi, 4);
    }

    @Test
    public void shouldInsertNewSuperheroToDB() {
        AddSuperHero addSuperHero = new AddSuperHero("Thor");

        addSuperHeroRepository.addSuperhero(addSuperHero);

        List<AddSuperHero> addSuperHeroes = addSuperHeroRepository.find();
        assertEquals(1, addSuperHeroes.size());
        assertEquals("Thor", addSuperHeroes.get(0).getMessage());

    }
}
