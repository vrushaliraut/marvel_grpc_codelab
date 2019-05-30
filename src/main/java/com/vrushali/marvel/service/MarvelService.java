package com.vrushali.marvel.service;

import com.example.grpc.Marvel.MarvelSuperheroRequest;
import com.example.grpc.Marvel.MarvelSuperheroResponse;
import com.vrushali.marvel.model.AddSuperHero;
import com.vrushali.marvel.repository.AddSuperHeroRepository;
import io.grpc.stub.StreamObserver;

import static com.example.grpc.MarvelSuperHeroServiceGrpc.MarvelSuperHeroServiceImplBase;

public class MarvelService extends MarvelSuperHeroServiceImplBase {

    private final AddSuperHeroRepository repository;

    public MarvelService(AddSuperHeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMarvelSuperHero(MarvelSuperheroRequest request,
                                   StreamObserver<MarvelSuperheroResponse> response){
        MarvelSuperheroResponse.Builder builder = MarvelSuperheroResponse.newBuilder();
        AddSuperHero superheroName = new AddSuperHero(request.getSuperheroName());
        repository.addSuperhero(superheroName);
        builder.setSuperheroName(request.getSuperheroName());
        response.onNext(builder.build());
        response.onCompleted();
    }
}
