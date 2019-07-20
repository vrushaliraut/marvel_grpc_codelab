package com.vrushali.marvel;

import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.Marvel.MarvelSuperheroResponse;
import com.example.grpc.MarvelSuperHeroServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class Client {
    private ManagedChannel channel;
    private final MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceBlockingStub blockingStub;

    public Client(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build());
    }

    Client(ManagedChannel channel) {
        blockingStub = MarvelSuperHeroServiceGrpc.newBlockingStub(channel);
        this.channel = channel;
    }

     private void shutdown() throws InterruptedException {
        channel.shutdown()
                .awaitTermination(5, TimeUnit.SECONDS);
    }

    void addMarvelSuperhero(String superhero_name) {
        MarvelSuperheroRequest request = MarvelSuperheroRequest.newBuilder().setSuperheroName(superhero_name).build();

        MarvelSuperheroResponse response;
        try {
            response = blockingStub.addMarvelSuperHero(request);
            System.out.println("Response " + response.getSuperheroName());
        } catch (RuntimeException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client("localhost", 8090);
        try {
            client.addMarvelSuperhero("save");
        } finally {
            client.shutdown();
        }
    }
}
