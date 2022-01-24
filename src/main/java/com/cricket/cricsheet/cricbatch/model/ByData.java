package com.cricket.cricsheet.cricbatch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ByData{
    private Integer runs;
	
    private Integer wickets;

	private Integer innings;

	public Integer getRuns() {
		return runs;
	}

	public void setRuns(Integer runs) {
		this.runs = runs;
	}

	public Integer getWickets() {
		return wickets;
	}

	public void setWickets(Integer wickets) {
		this.wickets = wickets;
	}

	public Integer getInnings() {
		return innings;
	}

	public void setInnings(Integer innings) {
		this.innings = innings;
	}
}
