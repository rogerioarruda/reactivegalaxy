package com.rogerioarruda.reactivegalaxy.components;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rogerioarruda.reactivegalaxy.models.Planet;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

@Component
public class CrudCommandLineRunner implements CommandLineRunner {
    private final DynamoDbAsyncClient asyncClient;
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;


    public CrudCommandLineRunner(DynamoDbAsyncClient asyncClient, DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        this.asyncClient = asyncClient;
        this.enhancedAsyncClient = enhancedAsyncClient;
    }

    @Override
    public void run(String... args) {
        CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();
        CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
        listCompletableFuture.thenAccept(tables -> {
            if (null != tables && !tables.contains(Planet.class.getSimpleName())) {
                DynamoDbAsyncTable<Planet> customer = enhancedAsyncClient.table(Planet.class.getSimpleName(), TableSchema.fromBean(Planet.class));
                customer.createTable();
            }
        });
    }
}