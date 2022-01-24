package com.cricket.cricsheet.cricbatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Officials {
	
	private List<String> match_referees;
	private List<String> tv_umpires;
	private List<String> umpires;
	private List<String> reserve_umpires;
	
	public List<String> getMatch_referees() {
		return match_referees;
	}
	public void setMatch_referees(List<String> match_referees) {
		this.match_referees = match_referees;
	}
	public List<String> getTv_umpires() {
		return tv_umpires;
	}
	public void setTv_umpires(List<String> tv_umpires) {
		this.tv_umpires = tv_umpires;
	}
	public List<String> getUmpires() {
		return umpires;
	}
	public void setUmpires(List<String> umpires) {
		this.umpires = umpires;
	}
	public List<String> getReserve_umpires() {
		return reserve_umpires;
	}
	public void setReserve_umpires(List<String> reserve_umpires) {
		this.reserve_umpires = reserve_umpires;
	}
	

}
