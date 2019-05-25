package com.vrushali.marvel.service;

import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.Marvel.MarvelSuperheroResponse;
import io.grpc.stub.StreamObserver;

import static com.example.grpc.MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceImplBase;

public class MarvelService extends MarvelSuperHeroServiceImplBase {

    @Override
    public void addMarvelSuperHero(MarvelSuperheroRequest request,
                                   StreamObserver<MarvelSuperheroResponse> response){
        MarvelSuperheroResponse.Builder builder = MarvelSuperheroResponse.newBuilder();
        builder.setSuperheroName(request.getSuperheroName());
        response.onNext(builder.build());
        response.onCompleted();
    }
}
