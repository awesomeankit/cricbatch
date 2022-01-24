package com.cricket.cricsheet.cricbatch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Outcome {
	
	private String result;
	private String winner;
	private String bowl_out;
	private String eliminator;
    private ByData by;
    private String method;
   
	public ByData getBy() {
        return by;
    }

    public void setBy(ByData by) {
        this.by = by;
    }
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBowl_out() {
		return bowl_out;
	}

	public void setBowl_out(String bowl_out) {
		this.bowl_out = bowl_out;
	}

	public String getEliminator() {
		return eliminator;
	}

	public void setEliminator(String eliminator) {
		this.eliminator = eliminator;
	}
}
