package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event;
import com.example.demo.model.EventDate;
import com.example.demo.model.Location;
import com.example.demo.model.Organization;
import com.example.demo.repo.EventRepo;
import com.example.demo.repo.OrganizationRepo;
import com.example.demo.controller.EventController;
import com.example.demo.model.EventPayload;

import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping("/ourevents")
public class EventController {
    
    @Autowired
    private EventRepo eventRepo;
    
    @Autowired
    private OrganizationRepo organizationRepo;
    
    Logger logger = LoggerFactory.getLogger(EventController.class);
    
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
    
    @GetMapping("/organizations")
    public List<Organization> getAllOrganizations() {
        return organizationRepo.findAll();
    }
	@PostMapping("/organizations/save")
	public Organization saveOrganization(@RequestBody Organization organization) {
		
		Organization organizationSaved = organizationRepo.save(organization);
		return organizationSaved;
	}   

	@DeleteMapping("/organizations/delete/{organizationtId}")
	public ResponseEntity<String> deleteOrganization(@PathVariable String organizationtId) {
	    Optional<Organization> organizationOptional = organizationRepo.findById(organizationtId);
	    
	    if (organizationOptional.isPresent()) {
	    	organizationRepo.deleteById(organizationtId);
	        return ResponseEntity.ok( organizationtId + " deleted successfully.");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PostMapping("/organizations/search")
	public List<Organization> searchOrganization(@RequestBody Organization org1){
		
		List<Organization> orgs=organizationRepo.findByNameContainsIgnoreCase(org1.getName());
		
		return orgs;
	}
	
	@GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }
	
	@PostMapping("/events/search")
	public List<Event> searchEvent(@RequestBody EventPayload payload){
		
		List<Event> events=eventRepo.findByNameContainsIgnoreCase(payload.getName());
		
		return events;
	}	
	
	
	@PostMapping("/events/save")
	public Event saveEvent(@RequestBody EventPayload payload)
	{
		
		Organization org = new Organization();
		org.setId(payload.getOrgid());
		
		
		Event eventToSave = new Event(payload.getName(), 
				payload.getIntro(), org, payload.getLoc(),payload.getDate());
		
		Event eventSaved= eventRepo.save(eventToSave);
		
		return eventSaved;
		
	}

	@DeleteMapping("/events/delete/{eventId}")
	public ResponseEntity<String> deleteEvent(@PathVariable String eventId) {
	    Optional<Event> eventOptional = eventRepo.findById(eventId);
	    
	    if (eventOptional.isPresent()) {
	        eventRepo.deleteById(eventId);
	        return ResponseEntity.ok(eventId + " deleted successfully.");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
		
	@GetMapping("/events/sort")
    public List<Event> sortedEvents() {
		return eventRepo.findAllByOrderByDateAsc();
	}
	
	@GetMapping("/eventscount")
    public long getEventCount() {
        return eventRepo.count();
    }
    
    
}
