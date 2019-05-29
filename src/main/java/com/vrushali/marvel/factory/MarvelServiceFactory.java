package com.vrushali.marvel.factory;

import com.gojek.ApplicationConfiguration;
import com.vrushali.marvel.service.MarvelService;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;

public class MarvelServiceFactory {

    public static ServerServiceDefinition instance(ApplicationConfiguration applicationConfiguration,
                                                   boolean enableMetricsReporting) {
        MarvelService marvelService = new MarvelService();
        return ServerInterceptors.intercept(marvelService);
    }
}
