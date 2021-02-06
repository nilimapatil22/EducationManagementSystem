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

/**
 * @author Prajakta
 *
 */
@Service
@Transactional
public class LoginService implements ILoginService {
	@Autowired
	UserRepository repository;
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Override
	public User getAdminLogin(int adminId, String password) {
		User user = repository.findById(adminId).orElse(null);
		if (user == null) {
			logger.warn("User with  id " + adminId + " not present");
			throw new DataNotFoundException("User with given id  not present");
		}
		if (!user.getRoleType().equals("admin")) {
			logger.warn("Invalid User Id");
			throw new DataNotFoundException("Invalid Id");
		}
		if (!user.getPassword().equals(password)) {
			logger.warn("User with  password  not present");
			throw new DataNotFoundException("Invalid Password");
		}

		return user;

	}

	@Override
	public User getStudentLogin(int studentId, String password) {
		User user = repository.findById(studentId).orElse(null);
		if (user == null) {
			logger.warn("User with  id " + studentId + " not present");

			throw new DataNotFoundException("User with given id  not present");
		} else if (!user.getPassword().equals(password)) {
			logger.warn("User with given  password not present");
		}
		if (!user.getRoleType().equals("student")) {
			logger.warn("Invalid Id");

			throw new DataNotFoundException("Invalid Id");
		}
		if (!user.getPassword().equals(password)) {
			logger.warn("User with  password  not present");
			throw new DataNotFoundException("Invalid Password");
		}

		return user;

	}

}