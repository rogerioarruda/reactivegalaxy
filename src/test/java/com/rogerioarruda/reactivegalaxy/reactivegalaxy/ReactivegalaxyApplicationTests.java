package com.rogerioarruda.reactivegalaxy.reactivegalaxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rogerioarruda.reactivegalaxy.models.Planet;
import com.rogerioarruda.reactivegalaxy.repositories.PlanetRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
class ReactivegalaxyApplicationTests {

	@Autowired
    PlanetRepository planetRepository;


	@Test
	public void whenSave_thenFindOneById() {
		Planet planet = saveOnePlanet();

		CompletableFuture<Planet> found = planetRepository.getPlanetById(planet.getId());
		try {
			assertEquals(planet.getId(), found.get().getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private Planet saveOnePlanet() {
		Planet planet = new Planet("DummyPlanet","DummyClimate","DummyTerrain",0);
		planetRepository.save(planet);
		return planet;
	}
}
