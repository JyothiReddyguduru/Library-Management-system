package com.booknow.controllers;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booknow.entities.FineRule;
import com.booknow.entities.BookLimitRule;
import com.booknow.services.AppConfigService;

/***
 * 
 * @author Jyothi
 * @createdon 11/16
 * @LastModified on 11/19
 * Entity to manage request mappings for app configuration such as fine policy and book linit policy 
 */

@RestController
public class AppConfigController {
	
	@Autowired
	private AppConfigService appConfigService;
	/**
	 * method to book limit policy entries from table
	 * @returnlist of Book limit policy rules
	 */
	@GetMapping("/appConfig/getBookLimitPolicy") 
	public List<BookLimitRule> getBookLimitPolicy() {
		return appConfigService.getBookLimitPolicy();
	}
	
	/**
	 * method to  fine policy entries from table
	 * @returnlist of fine policy rules
	 */
	@GetMapping("/appConfig/getfinePolicy") 
	public List<FineRule> getFinePolicy() {
		return appConfigService.getFinePolicy();
	}
	
	/**
	 * method to edit fine policies
	 * @param fineRule
	 */
	@PostMapping("/appConfig/editFineRule")
	public void editFineRule( @RequestBody FineRule fineRule) {
		appConfigService.editFineRule(fineRule);
	}
	
	/**
	 * method to edit book limit rules
	 * @param bookLimitRule
	 */
	@PostMapping("/appConfig/editBookLimitRule")
	public void editBookLimitRule( @RequestBody BookLimitRule bookLimitRule) {
		appConfigService.editBookLimitRule(bookLimitRule);
	}
	
	/**
	 * initial set up for polices
	 */
	@GetMapping("/appConfig/setup") 
	public void setupAppPolicies() {
		//appConfigService.setupBookLimitPolicy();
		appConfigService.setupFinePolicy();
	}
	
	

}
