package com.vrushali.marvel;


import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceImplBase;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {

    @Rule
    public final GrpcCleanupRule serverRule = new GrpcCleanupRule();
    private final MarvelSuperHeroServiceImplBase serviceImpl =
            mock(MarvelSuperHeroServiceImplBase.class, delegatesTo(new MarvelSuperHeroServiceImplBase() {
            }));
    private Client client;

    @Before
    public void setUp() throws Exception {
        String serverName = InProcessServerBuilder.generateName();

        serverRule.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(serviceImpl).build().start());


        ManagedChannel channel = serverRule.register(
                InProcessChannelBuilder.forName(serverName).directExecutor().build());

        client = new Client(channel);

    }

    @Test
    public void testShouldCallAddSuperhero() {

        ArgumentCaptor<MarvelSuperheroRequest> requestCaptor = ArgumentCaptor.forClass(MarvelSuperheroRequest.class);

        client.addMarvelSuperhero("Thor");

        verify(serviceImpl).addMarvelSuperHero(requestCaptor.capture(), any());

        assertEquals("Thor", requestCaptor.getValue().getSuperheroName());

    }
}
