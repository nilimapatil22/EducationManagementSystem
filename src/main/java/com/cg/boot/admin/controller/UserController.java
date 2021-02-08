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

/**
 * @author Prajakta
 *
 */
@RestController
@RequestMapping("/api")
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
		User userInfo = userService.addUser(userDetails);
		if(userInfo==null)
		{
			throw new DataNotFoundException("Invalid user information");
		}
		if(!userInfo.getRoleType().equals("Admin"))
		{
			throw new DataNotFoundException("you are not authorized");
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

	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.getUser(id);

		if (user == null) {
			logger.warn("User not found with ID " + id);

			throw new DataNotFoundException("No user present with given id: " + id);
		}
		

		logger.info("Admin Details found Successfully with ID " + id);
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

	/**
	 * This method accepts user Id to delete user details based on user Id It will
	 * check userId, if it is null then it will throw exception. Return list of
	 * remaining schedules except deleted one.
	 * 
	 * @param userId :{@link Integer}
	 * @return {@link ResponseEntity} user {@link List} {@link HttpStatus}
	 */

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int userId) {
		List<User> user = userService.deleteUser(userId);
		if (user == null) {
			logger.info("Admin found to delete with ID " + userId);
			throw new DataNotFoundException("No user present to delete with given id: " + userId);
		}
		logger.info("Admin id deleted successfully");
		return "user id deleted successfully";
	}

}
