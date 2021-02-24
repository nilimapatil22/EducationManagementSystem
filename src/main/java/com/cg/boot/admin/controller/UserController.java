package com.cg.boot.admin.controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.User;
import com.cg.boot.service.IUserService;

/**
 * @author Prajakta
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	@Autowired
	IUserService userService;
	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * This method accepts and saves admin details through object. Return an object
	 * of user containing all arguments which has been saved.
	 * 
	 * @param : userDetails {@link User}
	 * @return : User {@link User}
	 */

	@PostMapping("/add")
	public User addUser(@Valid @RequestBody User userDetails) {
		User userInfo = userService.addUserAdmin(userDetails);
		if (userInfo == null) {
			throw new DataNotFoundException("Invalid user information");
		}
		logger.info("Admin Added Successfully");
		return userInfo;
	}

	/**
	 * This method accepts user id which user has inserted. Return response entity
	 * containing user based on id
	 * 
	 * @param id {@link User}
	 * @return {@link ResponseEntity}: user {@link User} {@link HttpStatus}
	 */

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
		User user = userService.getUser(userId);

		if (user == null) {
			logger.warn("User not found with ID " + userId);

			throw new DataNotFoundException("No user present with given id: " + userId);
		}

		logger.info("Admin Details found Successfully with ID " + userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	/**
	 * This method will accepts and return list of all users
	 * 
	 * @return {@link ResponseEntity}: userList{@link List} {@link HttpStatus}
	 */

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		logger.info("All Admin Details return Successfully");
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	/**
	 * This method accepts and update user information which user has inserted
	 * through object. Return response entity containing details of user which has
	 * been updated.
	 * 
	 * @param user {@link User}
	 * @return {@link ResponseEntity} userInfo {@link HttpStatus}
	 */

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<User> updateUserDetails(@PathVariable("userId") int userId,@RequestBody User user) {
		User updateUser = userService.updateUserDetails(userId, user);
		if (updateUser == null) {
			logger.warn("Admin Details not found  to update");
			throw new DataNotFoundException("No user present to update");
		}
		logger.info("Admin updated Successfully ");
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
	}
	

	

	
}