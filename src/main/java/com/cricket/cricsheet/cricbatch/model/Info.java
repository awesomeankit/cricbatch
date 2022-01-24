package com.cricket.cricsheet.cricbatch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mongodb.BasicDBObject;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
	
	private Integer balls_per_over;
	private String city;
	private List<String> dates;
	private String gender;
	private String match_type;
	private Officials officials;
	private Outcome outcome;
	private Integer overs;
	private List<String> player_of_match;
	private BasicDBObject players;
	private BasicDBObject registry;
	private List<String> teams;
	private Toss toss;
	private String venue;
	private Event event;
	private List<BowlOut> bowl_out;
	private BasicDBObject supersubs;
	private Integer match_type_number;
	private String season;
	private String team_type;
	
	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMatch_type() {
		return match_type;
	}

	public void setMatch_type(String match_type) {
		this.match_type = match_type;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public Integer getOvers() {
		return overs;
	}

	public void setOvers(Integer overs) {
		this.overs = overs;
	}

	public List<String> getPlayer_of_match() {
		return player_of_match;
	}

	public void setPlayer_of_match(List<String> player_of_match) {
		this.player_of_match = player_of_match;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public Toss getToss() {
		return toss;
	}

	public void setToss(Toss toss) {
		this.toss = toss;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Integer getBalls_per_over() {
		return balls_per_over;
	}

	public void setBalls_per_over(Integer balls_per_over) {
		this.balls_per_over = balls_per_over;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Officials getOfficials() {
		return officials;
	}

	public void setOfficials(Officials officials) {
		this.officials = officials;
	}

	public BasicDBObject getPlayers() {
		return players;
	}

	public void setPlayers(BasicDBObject players) {
		this.players = players;
	}

	public BasicDBObject getRegistry() {
		return registry;
	}

	public void setRegistry(BasicDBObject registry) {
		this.registry = registry;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<BowlOut> getBowl_out() {
		return bowl_out;
	}

	public void setBowl_out(List<BowlOut> bowl_out) {
		this.bowl_out = bowl_out;
	}

	public BasicDBObject getSupersubs() {
		return supersubs;
	}

	public void setSupersubs(BasicDBObject supersubs) {
		this.supersubs = supersubs;
	}

	public Integer getMatch_type_number() {
		return match_type_number;
	}

	public void setMatch_type_number(Integer match_type_number) {
		this.match_type_number = match_type_number;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTeam_type() {
		return team_type;
	}

	public void setTeam_type(String team_type) {
		this.team_type = team_type;
	}
	
}
