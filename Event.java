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
	private Organizator org;
	
	private Location loc;
	private Date date;
	
	
	public Event(String name, String intro, Location loc, Date date) {
		super();
		this.name = name;
		this.intro = intro;
		this.loc = loc;
		this.date = date;
		
	}
	
	public Event() {
		
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
