package com.cg.boot.service;

import com.cg.boot.model.User;

public interface ILoginService {

	
	public User getStudentLogin(int studentId, String password);

	public User getAdminLogin(int adminId, String password);

}
