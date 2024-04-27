package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Event;
import com.example.demo.model.Organization;
import com.example.demo.repo.EventRepo;
import com.example.demo.repo.OrganizationRepo;

@Service
public class EventService {
    
    @Autowired
    private EventRepo eventRepo;
    
    @Autowired
    private OrganizationRepo organizationRepo;
    
    public Event generateEvent(String eventName, String organizationName, Date eventDate) {
        Organization organization = organizationRepo.findByName(organizationName);
        if (organization == null) {
            throw new IllegalArgumentException("Organization not found: " + organizationName);
        }
        
        Event event = new Event(eventName, organization, eventDate);
        return eventRepo.save(event);
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
