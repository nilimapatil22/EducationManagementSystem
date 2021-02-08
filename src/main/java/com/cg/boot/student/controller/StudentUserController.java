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

/**
 * @author Prajakta
 *
 */
@RestController
@RequestMapping("/api")
public class StudentUserController {
	@Autowired
	IUserService userService;
	Logger logger = LoggerFactory.getLogger(StudentUserController.class);

	/**
	 * This method accepts and saves User details through object. Return an object
	 * of user containing all arguments which has been saved.
	 * 
	 * @throws DataNotFoundException
	 * @param : userDetails {@link User}
	 * @return : User {@link User}
	 */

	@PostMapping("/addStudent")
	public User addUser(@Valid @RequestBody User userDetails) {
		User userInfo = userService.addUser(userDetails);
		if (userInfo == null) {
			throw new DataNotFoundException("User info should not be null");
		}
		if (!userInfo.getRoleType().equals("student")) {
			throw new DataNotFoundException("you are not authorized");
		}
		logger.info("Student Added Sucessfully");
		return userInfo;
	}

	/**
	 * This method accepts user id which user has inserted. Return response entity
	 * containing user based on id
	 * 
	 * @throws DataNotFoundException
	 * @param id {@link User}
	 * @return {@link ResponseEntity}: user {@link User} {@link HttpStatus}
	 */

	@GetMapping("/getStudent/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.getUser(id);
		if (user == null) {
			logger.warn("No user present with given id: " + id);
			throw new DataNotFoundException("No user present with given id: " + id);
		}
		logger.info("Student Details Return successfully with ID " + id);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	/**
	 * This method will accepts and return list of all users
	 * 
	 * @return {@link ResponseEntity}: userList{@link List} {@link HttpStatus}
	 */

	@GetMapping("/getAllStudent")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.getAllUsers();
		logger.info("Return All student Details successfully");
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	/**
	 * This method accepts and update user information which user has inserted
	 * through object. Return response entity containing details of user which has
	 * been updated.
	 * 
	 * @throws DataNotFoundException
	 * @param user {@link User}
	 * @return {@link ResponseEntity} userInfo {@link HttpStatus}
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

	/**
	 * This method accepts user Id to delete user details based on user Id It will
	 * check userId, if it is null then it will throw exception. Return list of
	 * remaining schedules except deleted one.
	 * 
	 * @throws DataNotFoundException
	 * @param userId :{@link Integer}
	 * @return {@link ResponseEntity} user {@link List} {@link HttpStatus}
	 */

	@DeleteMapping("/deleteStudent/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		List<User> user = userService.deleteUser(id);
		if (user == null) {
			logger.info("No user present to delete with given id: " + id);
			throw new DataNotFoundException("No user present to delete with given id: " + id);
		}
		logger.info("Delete student Details successfully with ID " + id);
		return "user id deleted successfully";
	}

}
