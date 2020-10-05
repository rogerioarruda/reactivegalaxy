package com.rogerioarruda.reactivegalaxy.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rogerioarruda.reactivegalaxy.dtos.SearchDTO;
import com.rogerioarruda.reactivegalaxy.models.Planet;
import com.rogerioarruda.reactivegalaxy.services.PlanetService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path="/planet")
public class PlanetController {
	
  private final PlanetService planetService;

  public PlanetController(PlanetService planetService) {
      this.planetService = planetService;
  }

  @PostMapping
  public void post (@RequestBody Planet planet) {	  
	  planetService.savePlanet(planet);
  }

  @GetMapping
  public @ResponseBody Flux<Planet> getAllPlanets() {
    return planetService.getPlanetList();
  }  
  
  @GetMapping(path="/{id}")
  public @ResponseBody Mono<Planet> getById(@PathVariable String id) {
    return planetService.getPlanetById(id);
  }

  @GetMapping(path="/getAllFromSwapi/")
  public @ResponseBody Mono<SearchDTO> getAllFromSwapi() {
    return planetService.getAllPlanetsFromSWAPI("");
  }
  
  @DeleteMapping(path="/{id}")
  public void deleteById(@PathVariable String id) {
    planetService.deletePlanetById(id);
  }
  
}
