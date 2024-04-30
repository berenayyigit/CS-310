package com.example.demo.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PutMapping("/organizations/update/{organizationId}")
	public ResponseEntity<Organization> updateOrganization(@PathVariable String organizationId, @RequestBody Organization updatedOrganization) {
	    Optional<Organization> organizationOptional = organizationRepo.findById(organizationId);

	    if (organizationOptional.isPresent()) {
	        Organization existingOrganization = organizationOptional.get();
	        existingOrganization.setName(updatedOrganization.getName()); // Update organization name or other fields

	        Organization updatedOrg = organizationRepo.save(existingOrganization);
	        return ResponseEntity.ok(updatedOrg);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
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
	
	@PutMapping("/events/update/{eventId}")
	public ResponseEntity<Event> updateEvent(@PathVariable String eventId, @RequestBody EventPayload payload) {
	    Optional<Event> eventOptional = eventRepo.findById(eventId);

	    if (eventOptional.isPresent()) {
	        Event existingEvent = eventOptional.get();
	        Organization org = new Organization();
	        org.setId(payload.getOrgid());

	        existingEvent.setName(payload.getName());
	        existingEvent.setIntro(payload.getIntro());
	        existingEvent.setOrg(org);
	        existingEvent.setLoc(payload.getLoc());
	        existingEvent.setDate(payload.getDate());

	        Event updatedEvent = eventRepo.save(existingEvent);
	        return ResponseEntity.ok(updatedEvent);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
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
	
    @GetMapping("/events/searchbydate")
    public List<Event> searchEventsByPartialDate(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String day) {

        // Call the repository method to fetch events by partial date
        List<Event> events = eventRepo.findByDateYearLikeAndDateMonthLikeAndDateDayLike(
                (year != null) ? year : "",
                (month != null) ? month : "",
                (day != null) ? day : ""
        );

        return events;
    }
    /*@PostMapping("/events/searchbydate")
	public List<Event> searchByDate(@RequestParam(required = false) String year,
	                                 @RequestParam(required = false) String month,
	                                 @RequestParam(required = false) String day,
									 @RequestParam(required = false) String hour,
	                                 @RequestParam(required = false) String minute)

{
	    List<Event> events;

	    if (year != null && month != null && day != null && hour != null && minute != null) {
	        // Search by year, month, day, hour, minute
	        events = eventRepo.findByDateYearAndMonthAndDayAndHourAndMinute(year, month, day, hour, minute);
	    } else if (year != null && month != null && day != null && hour != null) {
	        // Search by year, month, day, hour
	        events = eventRepo.findByDateYearAndMonthAndDayAndHour(year, month, day, hour);
	    } else if (year != null && month != null && day != null) {
	        // Search by year, month, day
	        events = eventRepo.findByDateYearAndMonthAndDay(year, month, day);
	    } else if (year != null && month != null) {
	        // Search by year and month
	        events = eventRepo.findByDateYearAndMonth(year, month);
	    } else if (year != null) {
	        // Search by year only
	        events = eventRepo.findByDateYear(year);
	    } else {
	        // Handle other cases or return all events
	        events = eventRepo.findAll(); 
	    }

	    return events;
	}*/

	
	@GetMapping("/eventscount")
    public long getEventCount() {
        return eventRepo.count();
    }
    
    
}
