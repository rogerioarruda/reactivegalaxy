package com.rogerioarruda.reactivegalaxy.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Planet {
	
	private String id;
	
	private String name;
	
	private String climate;
	
	private String terrain;
	
	private Integer qtFilms;
		
    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    @DynamoDbAttribute("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @DynamoDbAttribute("climate")
	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

    @DynamoDbAttribute("terrain")
	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

    @DynamoDbAttribute("qtFilms")
	public Integer getQtFilms() {
		return qtFilms;
	}

	public void setQtFilms(Integer qtFilms) {
		this.qtFilms = qtFilms;
	}

}
