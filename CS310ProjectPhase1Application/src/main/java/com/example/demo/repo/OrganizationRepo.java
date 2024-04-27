package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Event;
import com.example.demo.model.Organization;
import com.example.demo.repo.OrganizationRepo;

public interface OrganizationRepo extends MongoRepository<Organization, String>{
	public List<Event> findByName(String name);

}