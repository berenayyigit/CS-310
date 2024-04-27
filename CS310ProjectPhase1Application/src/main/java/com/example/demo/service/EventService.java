package com.example.demo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.EventDate;
import com.example.demo.model.Organization;
import com.example.demo.Cs310ProjectPhase1Application;
import com.example.demo.model.Event;
import com.example.demo.model.Location;
import com.example.demo.repo.EventRepo;
import com.example.demo.repo.OrganizationRepo;

import jakarta.annotation.PostConstruct;

@Service
public class EventService {
    
    @Autowired
    private EventRepo eventRepo;
    
    @Autowired
    private OrganizationRepo organizationRepo;
    
    Logger logger = LoggerFactory.getLogger(EventService.class);
    
    @PostConstruct
    public void init() {
    	if(organizationRepo.count()==0) {
    		
    		logger.info("No data");
    		
    		Organization org1 = new Organization("IES");
    		Organization org2 = new Organization("SUDANCE");
    		Organization org3 = new Organization("SUO");
    				
    		org1=organizationRepo.save(org1);
    		org2=organizationRepo.save(org2);
    		org3=organizationRepo.save(org3);
    		
    		logger.info("Organizations are saved");
    		
    		Location loc1 = new Location("FensG077");
    		Location loc2 = new Location("Studio");
    		Location loc3 = new Location("CinemaHall");
    		
    		EventDate d1 = new EventDate("2024","02","18","20","00");
    		EventDate d2 = new EventDate("2024","02","18","20","30");
    		EventDate d3 = new EventDate("2024","04","08","19","00");
    		
    		
    		Event e1 = new Event("IMIS", "Intro1", org1, loc1, d1);
    		Event e2 = new Event("Olum Opucugu", "Intro2", org3, loc3, d2);
    		Event e3 = new Event("Just Dance", "Intro3", org2, loc2, d3);
    		
    		e1 = eventRepo.save(e1);
    		e2 = eventRepo.save(e2);
    		e3 = eventRepo.save(e3);
    		
    		logger.info("Events are saved");
    		
    		
    	}
    }
    
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }
    
    public long getEventCount() {
        return eventRepo.count();
    }
    
    public void deleteEvent(String eventId) {
        eventRepo.deleteById(eventId);
    }
    
    public void updateEvent(Event event) {
        eventRepo.save(event);
    }
}