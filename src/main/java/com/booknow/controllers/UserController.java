package com.booknow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booknow.Views.UserView;
import com.booknow.entities.Principal;
import com.booknow.services.AuthService;
import com.fasterxml.jackson.annotation.JsonView;


/*
 * @Author - Jyothi Reddy Guduru*/

@RestController
public class UserController {
	
	@Autowired
	AuthService authService;
	
	
	@JsonView(UserView.class)
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal getUser() {
		return authService.getLoggedInUser();
	}

}
