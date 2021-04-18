package com.booknow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booknow.entities.BookHistory;
import com.booknow.entities.User;
import com.booknow.model.TestModel;
import com.booknow.services.UserService;

/**
 * Controller for calling user related services
 * 
 * @author Jyothi
 * @LastModifiedOn 11/21/2020
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * mapping to get user based on username and password
	 * @param user
	 * @return
	 */
	@PostMapping("/user")
	public User getUser(@RequestBody TestModel user) {
		return userService.getLoggedInUser(user);
	}

	/**
	 * mapping to gfetch all users frm fb
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	/**
	 * method to fetch my book hostory
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/myHistory", method = RequestMethod.GET)
	public List<BookHistory> getMyHistory(@RequestParam(name="name") String name) {
		return userService.getMyHistory(name);
	}
	
	/**
	 * method to fetch all book history
	 * @return
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public List<BookHistory> getHistory() {
		return userService.getHistory();
	}

	/**
	 * method to add a user to the application
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/addUser")
	public User getAllUsers(@RequestBody User user) {
		return userService.addUser(user);
	}

}
