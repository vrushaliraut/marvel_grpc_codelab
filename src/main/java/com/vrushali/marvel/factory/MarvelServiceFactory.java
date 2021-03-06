package com.vrushali.marvel.factory;

import com.gojek.ApplicationConfiguration;
import com.vrushali.marvel.repository.AddSuperHeroRepository;
import com.vrushali.marvel.service.MarvelService;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import org.skife.jdbi.v2.DBI;

import java.util.Collections;

public class MarvelServiceFactory {

    public static ServerServiceDefinition instance(ApplicationConfiguration appConfig) {
        final DBI dbi = new DBIFactory(appConfig).create();

        AddSuperHeroRepository repository = new AddSuperHeroRepository(dbi, 1);
        MarvelService marvelService = new MarvelService(repository);

        return ServerInterceptors.intercept(marvelService, Collections.emptyList());
    }
}
