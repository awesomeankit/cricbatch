package com.cricket.cricsheet.cricbatch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Toss {
	
    private String decision;
	
    private String winner;
    
    private boolean uncontested;

	public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

	public boolean isUncontested() {
		return uncontested;
	}

	public void setUncontested(boolean uncontested) {
		this.uncontested = uncontested;
	}
}