package com.rogerioarruda.reactivegalaxy.dtos;

import java.util.List;

public class SearchDTO {

    private Integer count;
    private String next;
    private String previous;
    private List<PlanetDTO> results;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public List<PlanetDTO> getResults() {
		return results;
	}
	public void setResults(List<PlanetDTO> results) {
		this.results = results;
	}
}
