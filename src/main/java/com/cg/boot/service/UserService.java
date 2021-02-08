package com.cg.boot.service;

import java.util.List;


import java.util.regex.Pattern;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;



/**
 * @author Prajakta
 *
 */
@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	UserRepository repository;
	Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method saves user details with passed objects. Check if phone number,
	 * role type, password is matched with the format given in method of validation,
	 * if not matched it will throw exception otherwise return User.
	 * 
	 * @param userDetails {@link User}
	 * @return User {@link User}
	 * @throws DataNotFoundException
	 */
	@Override
	public User addUser(User userDetails) {
		if (!validatePhoneNumber(userDetails.getPhoneNumber())) {
			logger.error("Invalid Phone Number");
			throw new DataNotFoundException("Invalid Phone Number");
		}
		if (!validateRoleType(userDetails.getRoleType())) {
			logger.error("Invalid Role Type");
			throw new DataNotFoundException("Invalid Role Type");
		}
		if (!validatePassword(userDetails.getPassword())) {
			logger.error("Invalid Password");
			throw new DataNotFoundException("Invalid Password");
		}
		return repository.save(userDetails);
	}

	/**
	 * This method finds user based on passed user Id. If user finds then return
	 * user and if not,return null.
	 * 
	 * @param userId {@link Integer}
	 * @return User {@link User}
	 */
	@Override
	public User getUser(int userId) {
		return repository.findById(userId).orElse(null);
	}

	/**
	 * This method find all users and return list of user.
	 * 
	 * @return {@link List} {@link User}
	 */
	@Override
	public List<User> getAllUsers() {
		return repository.findAll();

	}

	/**
	 * This method accepts user based on passed object it will update user details.
	 * Check if phone number,role type, password is matched with the format given in
	 * method of validation, if not matched it will throw exception otherwise return
	 * User.
	 * 
	 * @param user: {@link User}
	 * @return User {@link User}
	 * @throws DataNotFoundException
	 */
	@Override
	public User updateUserDetails(@Valid User user) {
		User userDetails = getUser(user.getUserId());
		if (userDetails != null) {
			if (!validatePhoneNumber(user.getPhoneNumber())) {
				logger.error("Invalid Phone Number");
				throw new DataNotFoundException("Invalid Phone Number");
			}
			if (!validateRoleType(user.getRoleType())) {
				logger.error("Invalid Role Type");
				throw new DataNotFoundException("Invalid Role Type");
			}
			if (!validatePassword(user.getPassword())) {
				logger.error("Invalid Password");
				throw new DataNotFoundException("Invalid Password");
			}

			userDetails = repository.save(user);
		}
		return userDetails;
	}

	/**
	 * This method delete user details based on passed id, If that id is not null.
	 * and Return list of all remaining user except deleted one.
	 * 
	 * @param userId: {@link Integer}
	 * @return {@link User} {@link List}
	 */

	@Override
	public List<User> deleteUser(int userId) {
		List<User> list = null;
		User userDetails = getUser(userId);
		if (userDetails != null) {
			repository.deleteById(userId);
			list = getAllUsers();
		}
		return list;
	}

	/**
	 * This method perform validation of phone number, It will first convert data
	 * type of phone number to string then match with regular expression by using
	 * Pattern. matches, If it is matched it will return flag as true or else return
	 * flag as false.
	 * 
	 * @param phoneNumber {@link Long}
	 * @return flag {@link Boolean}
	 */
	public boolean validatePhoneNumber(long phoneNumber) {
		boolean flag = false;
		String number = String.valueOf(phoneNumber);
		String regex = "[7-9]{1}[0-9]{9}";
		if (Pattern.matches(regex, number)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * This method performs validation of role type. It will check if the role type
	 * is admin or student If it matches then return flag as true otherwise return
	 * flag as false.
	 * 
	 * @param roleType {@link String}
	 * @return flag {@link Boolean}
	 */
	public boolean validateRoleType(String roleType) {
		boolean flag = false;

		if ((roleType.matches("admin")) || (roleType.matches("student"))) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * This method performs validation of password.It will check password, If it is
	 * matched with given format then it will flag as true otherwise return flag as
	 * false.
	 * 
	 * @param password {@link String}
	 * @return flag {@link Boolean}
	 */

	public boolean validatePassword(String password) {
		boolean flag = false;
		String passregex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		if (Pattern.matches(passregex, password)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * This method perform validation of adminId. If it is null and if role type is
	 * not equal to admin then it will throw customized exception otherwise return
	 * true.
	 * 
	 * @param adminId {@link Integer}
	 * @return {@link Boolean}
	 * @throws DataNotFoundException
	 */
	public boolean validateAdminId(int adminId) {
		User userInfo = getUser(adminId);
		if (userInfo == null) {
			logger.warn("Invalid user id");
			throw new DataNotFoundException("Invalid user id");
		}
		if (!userInfo.getRoleType().equals("admin")) {
			logger.warn("Invalid RoleType");
			throw new DataNotFoundException("You are not authorized");
		}
		return true;
	}

	/**
	 * This method perform validation of student id. If it is null and if role type
	 * is not equal to student then it will throw customized exception otherwise
	 * return true.
	 * 
	 * @param studentId {@link Integer}
	 * @return {@link Boolean}
	 * @throws DataNotFoundException
	 */
	public boolean validateStudentId(int studentId) {
		User userInfo = getUser(studentId);
		if (userInfo == null) {
			logger.warn("Invalid Student id");
			throw new DataNotFoundException("Invalid Student id");
		}
		if (!userInfo.getRoleType().equals("student")) {
			logger.warn("Invalid Role Type");
			throw new DataNotFoundException("Student id is not valid");
		}
		return true;
	}
	
	

}
