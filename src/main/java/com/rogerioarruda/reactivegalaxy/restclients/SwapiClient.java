package com.rogerioarruda.reactivegalaxy.restclients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.rogerioarruda.reactivegalaxy.dtos.SearchDTO;

import reactor.core.publisher.Mono;

@Component
public class SwapiClient {

    @Value("${swapi.url.planet}")
    private String valueFromFile;

    public Mono<SearchDTO>  findPlanet(String search) {

        search = valueFromFile + (search.trim().length() > 0 ? ("?search=" + search) : "");
        return WebClient.create(search)
                .get()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SearchDTO.class);
    }
}
