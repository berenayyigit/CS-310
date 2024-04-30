package com.example.demo.service;


import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.UserException;
import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {

	
	@Autowired UserRepo userRepo;
	
	
	public User loginUser(User user) throws Exception{
		User userFound = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(userFound==null) {
			throw new UserException("Username or password is wrong.");
		}
		
		String tokenText = new ObjectId().toString();
		Token token = new Token(tokenText,LocalDateTime.now().plusDays(100));
		userFound.setToken(token);
		userFound = userRepo.save(userFound);

		return userFound;
		
	}
	
	
	public boolean registerUser(User user) throws Exception {
		
		try {
			userRepo.save(user);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			throw new UserException("Username already exists.");
		}catch (Exception e) {
			throw e;
		}
		
		return true;
	}
	
	public User getUserByToken(String tokenStr) {
		User userFound = userRepo.findByTokenString(tokenStr);
		return userFound;
		
	}
	
	public void destroyToken(String tokenStr) {
		User userFound = userRepo.findByTokenString(tokenStr);
		
		userFound.setToken(null);
		
		userRepo.save(userFound);
		
	}
	

	
	
	
	
}