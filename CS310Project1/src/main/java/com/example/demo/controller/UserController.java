package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Organization;
import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.repo.UserRepo;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService userService;
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@PostMapping("/login")
	public Payload<Token> loginUser(@RequestBody User user){
		
		try {
			user = userService.loginUser(user);
		} catch (Exception e) {
			throw new UserException(e.getMessage());
		}
		
		return new Payload<Token>(LocalDateTime.now(),"OK",user.getToken());
		
	}
	
	@Autowired private UserRepo userRepo;
	@GetMapping("/alluser")
    public List<User> getAllUser() {
        return userRepo.findAll();
	}
	
	@GetMapping("/logout")
	public Payload<String> logoutUser(@RequestHeader String token){

		userService.destroyToken(token);
		
		
		return new Payload<String>(LocalDateTime.now(),"OK","Token destroyed.");
		
	}
	
	@PostMapping("/register")
	public Payload<String> registerUser(@RequestBody User user) throws UserException{
		
		if(user.getUsername()==null ||  
				user.getPassword()==null || 
				user.getName()==null || 
				user.getLastName() ==null) {
			logger.error("User field problem");
			throw new UserException("All fields are required.");
		}
		
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		
			throw new UserException(e.getMessage());
		}
		
		return new Payload<String>(LocalDateTime.now(),"OK", "User registered.");
		
		
	}
	
	
	
}