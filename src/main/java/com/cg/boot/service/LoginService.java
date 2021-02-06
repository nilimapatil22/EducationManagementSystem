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
	
 /**
  * This method performs login operation of admin only. It finds user by admin Id. If 
  * it is null or  role type is not admin then it will throw exception 
  * otherwise  return admin user.
  * 
  * @param studentId {@link Integer}
  * @param password {@link String}
  * @return studentUser {@link User}
  * @throws DataNotFoundException
  */
	@Override
	public User getAdminLogin(int adminId, String password) {
		User adminUser = repository.findById(adminId).orElse(null);
		if (adminUser == null) {
			logger.warn("User with  id " + adminId + " not present");
			throw new DataNotFoundException("User with given id  not present");
		}
		if (!adminUser.getRoleType().equals("admin")) {
			logger.warn("Invalid User Id");
			throw new DataNotFoundException("Invalid Id");
		}
		if (!adminUser.getPassword().equals(password)) {
			logger.warn("User with  password  not present");
			throw new DataNotFoundException("Invalid Password");
		}

		return adminUser;

	}
	
	/**
	  * This method performs login operation of student only. It finds user by student Id. If 
	  * it is null or  role type is not student then it will throw exception 
	  * otherwise  return student user.
	  * 
	  * @param adminId {@link Integer}
	  * @param password {@link String}
	  * @return adminUser {@link User}
	  * @throws DataNotFoundException
	  */

	@Override
	public User getStudentLogin(int studentId, String password) {
		User studentUser = repository.findById(studentId).orElse(null);
		if (studentUser == null) {
			logger.warn("User with  id " + studentId + " not present");

			throw new DataNotFoundException("User with given id  not present");
		} else if (!studentUser.getPassword().equals(password)) {
			logger.warn("User with given  password not present");
		}
		if (!studentUser.getRoleType().equals("student")) {
			logger.warn("Invalid Id");

			throw new DataNotFoundException("Invalid Id");
		}
		if (!studentUser.getPassword().equals(password)) {
			logger.warn("User with  password  not present");
			throw new DataNotFoundException("Invalid Password");
		}

		return studentUser;

	}

}