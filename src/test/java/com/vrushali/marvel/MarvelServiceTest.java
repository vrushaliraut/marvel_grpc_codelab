package com.vrushali.marvel;

import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.Marvel.MarvelSuperheroResponse;
import com.example.grpc.MarvelSuperHeroServiceGrpc;
import com.example.grpc.MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceBlockingStub;
import com.vrushali.marvel.factory.DBIFactory;
import com.vrushali.marvel.repository.MarvelSuperheroRepository;
import com.vrushali.marvel.service.MarvelService;
import io.grpc.BindableService;
import io.grpc.Channel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MarvelServiceTest {

    @Mock
    private MarvelSuperheroRepository superheroRepository;

    @Test
    public void testShouldCallGRPCServer() throws IOException {
        Server inProcessServer = InProcessServerBuilder
                .forName("MarvelServiceTest")
                .addService((BindableService) new MarvelService(superheroRepository))
                .build();

        Channel inProcessChannel = InProcessChannelBuilder
                .forName("MarvelServiceTest")
                .directExecutor()
                .build();

        inProcessServer.start();

        MarvelSuperHeroServiceBlockingStub blockingStub = MarvelSuperHeroServiceGrpc
                .newBlockingStub(inProcessChannel);

        MarvelSuperheroRequest request = MarvelSuperheroRequest
                .newBuilder()
                .setSuperheroName("Thor")
                .build();

        MarvelSuperheroResponse actualResponse = blockingStub.addMarvelSuperHero(request);

        assertNotNull(actualResponse);
        assertEquals("Thor", actualResponse.getSuperheroName());
    }
}
