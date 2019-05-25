package com.vrushali.marvel.service;

import com.example.grpc.Marvel;
import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.Marvel.MarvelSuperheroResponse;
import com.example.grpc.MarvelSuperHeroServiceGrpc;
import com.example.grpc.MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceBlockingStub;
import io.grpc.BindableService;
import io.grpc.Channel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MarvelServiceTest {

    @Test
    public void testShouldCheckMCallServer() throws IOException {
        Server inProcessServer = InProcessServerBuilder.forName("MarvelServiceTest").addService((BindableService) new MarvelService()).build();
        Channel inProcessChannel = InProcessChannelBuilder.forName("MarvelServiceTest").directExecutor().build();
        inProcessServer.start();
        MarvelSuperHeroServiceBlockingStub blockingStub = MarvelSuperHeroServiceGrpc.newBlockingStub(inProcessChannel);
        MarvelSuperheroRequest request = MarvelSuperheroRequest.newBuilder().setSuperheroName("Thor").build();

        MarvelSuperheroResponse actualResponse = blockingStub.addMarvelSuperHero(request);

        assertNotNull(actualResponse);
        assertEquals("Thor", actualResponse.getSuperheroName());
    }
}
