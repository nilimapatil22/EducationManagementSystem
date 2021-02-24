package com.cg.boot.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.boot.model.User;
/**
 * @author Prajkta. This interface contains abstract methods UserService
 *         class.
 *
 */
public interface IUserService {

	User getUser(int id);

	List<User> getAllUsers();

	List<User> deleteUser(int id);

	User addUserAdmin(User userDetails);

	User addUserStudent(User userDetails);

//	User updateUserDetailsStudent(@Valid User user);
//
//	User updateUserDetailsAdmin(@Valid User user);

	User updateUserDetails(int userId, User user);

}
