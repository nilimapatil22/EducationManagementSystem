package com.cg.boot.admin.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.model.User;
import com.cg.boot.service.ILoginService;

/**
 * @author prajakta
 *
 */
@RestController
@RequestMapping
public class LoginController {
	@Autowired
	ILoginService loginService;
	Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/getUser/{id}/{password}")
	public String getUser(@PathVariable("id") int id,@PathVariable("password") String password){
		@SuppressWarnings("unused")
		User user = loginService.getUser(id,password);
		logger.info("Login Successfully");
		return "Login Successful";
		
	}
	

}

