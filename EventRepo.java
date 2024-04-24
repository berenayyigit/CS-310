package com.example.demo.repo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.model.Event;
import com.example.demo.model.Date;

public interface EventRepo extends MongoRepository<Event, String>{
	public List<Event> findByName(String name);
	
	public List<Event> findByNameContainsIgnoreCase(String name);

	public List<Event> findByDateBetween(Date d1, Date d2);
}
