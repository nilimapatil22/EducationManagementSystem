package com.cg.boot.service;

import com.cg.boot.model.User;
/**
 * @author Prajkta. This interface contains abstract methods LoginService
 *         class.
 *
 */
public interface ILoginService {

	
	public User getStudentLogin(int studentId, String password);

	public User getAdminLogin(int adminId, String password);

}
