package com.cg.boot.student.controller;

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
public class StudentUserController {
	@Autowired
	IUserService userService;
	Logger logger=LoggerFactory.getLogger(StudentUserController.class);
	/*
	 * Add User
	 */

	@PostMapping("/addStudent")
	public User addUser(@Valid @RequestBody User userDetails) {
		User userInfo = userService.addUser(userDetails);
		logger.info("Student Added Sucessfully");
		return userInfo;
	}

	/*
	 * Get user based on id
	 */

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.getUser(id);
		if (user == null) {
			logger.warn("No user present with given id: " + id);
			throw new DataNotFoundException("No user present with given id: " + id);
		}
		logger.info("Student Details Return successfully with ID "+id);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	/*
	 * Get all user
	 */

	@GetMapping("/getAllStudent")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		logger.info("Return All student Details successfully");
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	/*
	 * Update User Details
	 */

	@PutMapping("/updateStudent")
	public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user) {
		User userInfo = userService.updateUserDetails(user);
		if (userInfo == null) {
			logger.warn("Student Details not found to update");
			throw new DataNotFoundException("No user present to update");
		}
		logger.info("Updated All student Details successfully");
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}

	/*
	 * Delete User
	 */

	@DeleteMapping("/deleteStudent/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		List<User> user = userService.deleteUser(id);
		if (user == null) {
			logger.info("No user present to delete with given id: " + id);
			throw new DataNotFoundException("No user present to delete with given id: " + id);
		}
		logger.info("Delete student Details successfully with ID "+id);
		return "user id deleted successfully";
	}

}
