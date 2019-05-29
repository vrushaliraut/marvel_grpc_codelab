package com.vrushali.marvel.factory;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.sun.javafx.collections.UnmodifiableListSet;
import com.vrushali.marvel.repository.MarvelSuperheroRepository;
import com.vrushali.marvel.service.MarvelService;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import org.skife.jdbi.v2.DBI;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MarvelServiceFactory {
    public static ServerServiceDefinition instance(ApplicationConfiguration applicationConfiguration,
                                                   boolean enableMetricsReporting) {
        final ApplicationConfiguration appConfig =
                Figaro.configure(new UnmodifiableListSet<>(getMandatoryVariables()));
        final DBI dbi = new DBIFactory(appConfig).create();
        final Integer marvel_timeout_in_sec =
                appConfig.getValueAsInt("MARVEL_TIMEOUT_IN_SEC", 1);
        MarvelSuperheroRepository repository = new MarvelSuperheroRepository(dbi, marvel_timeout_in_sec);
        MarvelService marvelService = new MarvelService(repository);
        return ServerInterceptors.intercept(marvelService, Collections.emptyList());
    }

    private static List<String> getMandatoryVariables() {
        return Arrays.asList("DB_HOST",
                "DB_PASSWORD",
                "DB_USERNAME",
                "LEAK_THRESHOLD",
                "DB_MAX_POOL_SIZE",
                "DB_NAME",
                "DB_PORT",
                "MARVEL_TIMEOUT_IN_SEC");
    }

}
