package com.example.demo.repo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.model.Event;
import com.example.demo.model.EventDate;
import com.example.demo.model.Organization;

public interface EventRepo extends MongoRepository<Event, String>{
	public List<Event> findByName(String name);
	
	public List<Event> findByNameContainsIgnoreCase(String name);
	
	/*
	public List<Event> findAllByOrg(Organization org);
	*/
	
	public List<Event> findAllByOrderByDateAsc();
	
	/*public List<Event> findByDateYearAndMonthAndDayAndHourAndMinute(String year, String month, String day, String hour, String minute);
	public List<Event> findByDateYearAndMonthAndDayAndHour(String year, String month, String day, String hour);
	public List<Event> findByDateYearAndMonthAndDay(String year, String month, String day);
	public List<Event> findByDateYearAndMonth(String year, String month);
	public List<Event> findByDateYear(String year);
	*/
	public List<Event> findByDateYearLikeAndDateMonthLikeAndDateDayLike(
	        String year, String month, String day
	    );
	/*
	public List<Event> findAllByDate(EventDate date);
	*/

	/*
	public List<Event> findByEventDateBetween(EventDate d1, EventDate d2);
	
	*/
	
	
	@Query("{'name':?0,'date':?1}")
	public List<Event> searchByNameAndDateExactly(String name, EventDate date);
	
	/*
	@Query("{'org':?0,'date':?1}")
	public List<Event> searchByOrganizatorAndEventDateExactly(Organization org, EventDate date);
	*/
	
}