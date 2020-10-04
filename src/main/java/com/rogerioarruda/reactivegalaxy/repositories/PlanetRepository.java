package com.rogerioarruda.reactivegalaxy.repositories;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Repository;

import com.rogerioarruda.reactivegalaxy.models.Planet;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

@Repository
public class PlanetRepository {

    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<Planet> customerDynamoDbAsyncTable;

    public PlanetRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.customerDynamoDbAsyncTable = enhancedAsyncClient.table(Planet.class.getSimpleName(), TableSchema.fromBean(Planet.class));
    }

    public CompletableFuture<Void> save(Planet planet) {
        return customerDynamoDbAsyncTable.putItem(planet);
    }

    public CompletableFuture<Planet> getPlanetById(String id) {
        return customerDynamoDbAsyncTable.getItem(getKeyBuild(id));
    }

    public CompletableFuture<Planet> updatePlanet(Planet planet) {
        return customerDynamoDbAsyncTable.updateItem(planet);
    }

    public CompletableFuture<Planet> deletePlanetById(String id) {
        return customerDynamoDbAsyncTable.deleteItem(getKeyBuild(id));
    }

    public PagePublisher<Planet> getAllPlanets() {
        return customerDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(String id) {
        return Key.builder().partitionValue(id).build();
    }

}