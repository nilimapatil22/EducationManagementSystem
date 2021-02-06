package com.cg.boot.service;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.admin.controller.LoginController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;

@Service
@Transactional
public class LoginService implements ILoginService {
	@Autowired
	UserRepository repository;
	Logger logger=LoggerFactory.getLogger(LoginController.class);

	@Override
	public User getUser(int id, String password) {
		User user = repository.findById(id).orElse(null);
		if (user == null) {
			logger.warn("User with  id "+id+" not present");
			throw new DataNotFoundException("User with given id  not present");
		} else if (!user.getPassword().equals(password)) {
			logger.warn("User with given  password not present");
			throw new DataNotFoundException("Invalid Password");
		}

		return user;

	}

}