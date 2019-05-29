package com.vrushali.marvel.service;

import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.Marvel.MarvelSuperheroResponse;
import com.vrushali.marvel.factory.DBIFactory;
import com.vrushali.marvel.model.Superhero;
import com.vrushali.marvel.repository.MarvelSuperheroRepository;
import io.grpc.stub.StreamObserver;

import static com.example.grpc.MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceImplBase;

public class MarvelService extends MarvelSuperHeroServiceImplBase {

    private final MarvelSuperheroRepository repository;

    public MarvelService(MarvelSuperheroRepository repository) {

        this.repository = repository;
    }

    @Override
    public void addMarvelSuperHero(MarvelSuperheroRequest request,
                                   StreamObserver<MarvelSuperheroResponse> response){
        MarvelSuperheroResponse.Builder builder = MarvelSuperheroResponse.newBuilder();
        Superhero superheroName = new Superhero(request.getSuperheroName());
        repository.saveMarvelSuperheroes(superheroName);
        builder.setSuperheroName(request.getSuperheroName());
        response.onNext(builder.build());
        response.onCompleted();
    }
}
