package com.example.demo.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.User;


public interface UserRepo extends MongoRepository<User,String> {

	public User findByUsernameAndPassword(String username,String password);
	@Query("{'token.token':?0}")
	public User findByTokenString(String tokenStr);
	
}