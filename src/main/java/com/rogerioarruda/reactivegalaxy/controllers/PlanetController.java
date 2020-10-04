package com.rogerioarruda.reactivegalaxy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rogerioarruda.reactivegalaxy.models.Planet;
import com.rogerioarruda.reactivegalaxy.services.PlanetService;

import reactor.core.publisher.Flux;


@RestController
public class PlanetController {
	
  private final PlanetService planetService;

  public PlanetController(PlanetService planetService) {
      this.planetService = planetService;
  }

  @PostMapping("/save")
  public @ResponseBody void post (@RequestBody Planet planet) {	  
	  planetService.savePlanet(planet);
  }

  @GetMapping
  public @ResponseBody Flux<Planet> getAllPlanets() {
    return planetService.getPlanetList();
  }  
  
}
