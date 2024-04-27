package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
*/

import com.example.demo.model.EventDate;
import com.example.demo.model.Organization;
/*
import com.example.demo.model.Event;
import com.example.demo.model.Organizator;
*/
import com.example.demo.repo.EventRepo;
import com.example.demo.repo.OrganizationRepo;
import com.example.demo.service.EventService;

@SpringBootApplication
public class Cs310ProjectPhase1Application implements CommandLineRunner{
	
	@Autowired EventService eventService;
	
	Logger logger = LoggerFactory.getLogger(Cs310ProjectPhase1Application.class);
	
	@Autowired EventRepo eventRepo;
	
	@Autowired OrganizationRepo organizationRepo;

	public static void main(String[] args) {
		SpringApplication.run(Cs310ProjectPhase1Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		
		logger.info("-----------------------");
		
		eventService.getAllEvents().forEach(s->{
			logger.info(s.getName().toString());
		});
		
		logger.info("-----------------------");
		
		eventRepo.findByName("IMIS").forEach(s->logger.info(s.getName().toString()));
		
		logger.info("-----------------------");
		
		
		eventRepo.findAllByOrderByDateAsc().forEach(s->logger.info(s.getName().toString()));
		
		logger.info("-----------------------");
		
		EventDate d1 = new EventDate("2024","02","18","20","00");
		eventRepo.searchByNameAndDateExactly("IMIS", d1).forEach(s->logger.info(s.getName().toString()));
		
	}

}
