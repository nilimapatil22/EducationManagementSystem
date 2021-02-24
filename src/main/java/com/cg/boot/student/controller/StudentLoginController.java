package com.cg.boot.student.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.model.User;
import com.cg.boot.service.ILoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentLoginController {
	@Autowired
	ILoginService loginService;
	Logger logger=LoggerFactory.getLogger(StudentLoginController.class);
	
	/**
	 * This method accepts student id and password based on passed object and 
	 * Return message as "Login Successful".
	 * @param studentId
	 * @param password
	 * @return "Login Successful" {@link String}
	 */
	
	@GetMapping("/getStudent/{id}/{password}")
	public  ResponseEntity<User> getUser(@PathVariable("id") int studentId,@PathVariable("password") String password){
		@SuppressWarnings("unused")
		User user = loginService.getStudentLogin(studentId,password);
		logger.info("Student Login Successfully");
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	

}

