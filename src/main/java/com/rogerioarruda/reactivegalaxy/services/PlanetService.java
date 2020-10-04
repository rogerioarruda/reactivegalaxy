package com.rogerioarruda.reactivegalaxy.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.rogerioarruda.reactivegalaxy.models.Planet;
import com.rogerioarruda.reactivegalaxy.repositories.PlanetRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetService {

	private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public void savePlanet(Planet planet) {
    	planetRepository.save(planet);
    }

    public Mono<Planet> getPlanetById(String id) {
        CompletableFuture<Planet> planet = planetRepository.getPlanetById(id)
                .whenComplete((pla, ex) -> {
                    if (null == pla) {
                        throw new IllegalArgumentException("Id inválido");
                    }
                })
                .exceptionally(ex -> new Planet());
        return Mono.fromFuture(planet);
    }

    public void deletePlanetById(String id) {
        planetRepository.deletePlanetById(id);
    }

    public Flux<Planet> getPlanetList() {
        return Flux.from(planetRepository.getAllPlanets().items())
                .onErrorReturn(new Planet());
    }

}