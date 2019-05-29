package com.vrushali.marvel.repository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.skife.jdbi.v2.DBI;

public class DataStoreRepositoryTest {

    protected static DBI dbi;

    @BeforeClass
    public static void beforeClass() {
        dbi = DBSetup.getDBI();
    }

    @Before
    public void before() {
        clear();
    }

    private void clear() {
        dbi.withHandle(handle -> {
            handle.createScript("clearTables.sql").execute();
            return null;
        });
    }
}
