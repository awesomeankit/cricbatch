package com.cricket.cricsheet.cricbatch.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;

@JsonInclude(Include.NON_NULL)
public class Match implements ResourceAware{

	@Id
	@JsonIgnore
	public ObjectId _id;
	
	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("info")
	private Info info;
	
	@JsonProperty("innings")
	private List<BasicDBObject> innings;
	
    private Date createdDate;
	
	private String from_filename;
	
	@Transient
	private Resource resource;
	
	public String getFrom_filename() {
		return from_filename;
	}

	public void setFrom_filename(String from_filename) {
		this.from_filename = from_filename;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public List<BasicDBObject> getInnings() {
		return innings;
	}

	public void setInnings(List<BasicDBObject> innings) {
		this.innings = innings;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setResource(Resource resource) {
		this.resource= resource;
	}
	
	public Resource getResource() {
		return this.resource;
	}
}
