package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {
    
    private String loc;
        
    
    public Location() {
    }

    public Location(String loc) {
        this.loc = loc;
        
    }


    public String getLocation() {
        return loc;
    }

    public void setLocation(String loc) {
        this.loc = loc;
    }
}
