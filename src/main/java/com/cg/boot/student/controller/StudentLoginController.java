package com.cg.boot.student.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.model.User;
import com.cg.boot.service.ILoginService;

@RestController
@RequestMapping
public class StudentLoginController {
	@Autowired
	ILoginService loginService;
	Logger logger=LoggerFactory.getLogger(StudentLoginController.class);
	
	@GetMapping("/getStudent/{id}/{password}")
	public String getUser(@PathVariable("id") int studentId,@PathVariable("password") String password){
		@SuppressWarnings("unused")
		User user = loginService.getStudentLogin(studentId,password);
		logger.info("Student Login Successfully");
		return "Login Successful";
		
	}
	

}

