package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.LoginFilter;
import com.example.demo.service.UserService;

@SpringBootApplication
public class Cs310Project1Application{
	
	public static void main(String[] args) {
		SpringApplication.run(Cs310Project1Application.class, args);
	}
	
	@Bean
	FilterRegistrationBean<LoginFilter> loginFilter(UserService userService){
	    FilterRegistrationBean<LoginFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new LoginFilter(userService));
	    registrationBean.addUrlPatterns("/user/userinfo/*");
	    registrationBean.addUrlPatterns("/contact/*");
	    registrationBean.setOrder(1);
	        
	    return registrationBean;    
	}

}