package com.example.demo.repo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.model.Event;
import com.example.demo.model.Date;
import com.example.demo.model.Organizator;

public interface EventRepo extends MongoRepository<Event, String>{
	public List<Event> findByName(String name);
	
	public List<Event> findByNameContainsIgnoreCase(String name);
	
	public List<Event> findAllByOrganizator(Organizator org);
	
	public List<Event> findAllByDate(Date date);

	public List<Event> findByDateBetween(Date d1, Date d2);
	
	public List<Event> findAllByOrderByDateAsc(); 
	
	@Query("{'name':?0,'date':?1}")
	public List<Event> searchByNameAndDateExactly(String name, Date date);
	
	@Query("{'org':?0,'date':?1}")
	public List<Event> searchByOrganizatorAndDateExactly(Organizator org, Date date);
	
}

