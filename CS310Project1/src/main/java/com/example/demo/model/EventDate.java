package com.example.demo.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EventDate {
    

	public String year;
	public String month;
	public String day;
	public String hour;
	public String minute;	
	public String time;
	
	public EventDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventDate(String year, String month, String day, String hour, String minute) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.time = year+"-"+month+"-"+day+"-"+hour+"-"+minute;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	

}