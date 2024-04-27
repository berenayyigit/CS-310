package com.example.demo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
	
	@Id
	private String id;
	private String name;
	private String intro;
	
	@DBRef
	private Organization org;
	
	private Location loc;
	private EventDate date;
	
	
	public Event(String name, String intro, Organization org, Location loc, EventDate date) {
		super();
		this.name = name;
		this.intro = intro;
		this.org = org;
		this.loc = loc;
		this.date = date;	
	}
	
	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Location getLoc() {
		return loc;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public EventDate getDate() {
		return date;
	}
	public void setDate(EventDate date) {
		this.date = date;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
	
	

}
