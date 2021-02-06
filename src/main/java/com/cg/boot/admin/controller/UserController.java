package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	IUserService userService;
	Logger logger=LoggerFactory.getLogger(UserController.class);
	/*
	 * delete User based on user Id
	 */

	@PostMapping("/add")
	public User addUser(@Valid @RequestBody User userDetails) {
		User userInfo = userService.addUser(userDetails);
		logger.info("Admin Added Successfully");
		return userInfo;
	}

	/*
	 * Get user based on id
	 */

	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.getUser(id);
		if (user == null) {
			logger.warn("Course not found with ID "+id);
			throw new DataNotFoundException("No user present with given id: " + id);
		}
		logger.info("Admin Details found Successfully with ID "+id);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	/*
	 * Get all user
	 */

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		logger.info("All Admin Details return Successfully");
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	/*
	 * Update User Details
	 */

	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user) {
		User userInfo = userService.updateUserDetails(user);
		if (userInfo == null) {
			logger.warn("Admin Details not found  to update");
			throw new DataNotFoundException("No user present to update");
		}
		logger.info("Admin updated Successfully ");
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}

	/*
	 * Delete User
	 */

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		List<User> user = userService.deleteUser(id);
		if (user == null) {
			logger.info("Admin found to delete with ID "+id);
			throw new DataNotFoundException("No user present to delete with given id: " + id);
		}
		logger.info("Admin id deleted successfully");
		return "user id deleted successfully";
	}

}
