package com.cg.boot.service;

import com.cg.boot.model.User;

public interface ILoginService {

	public User getUser(int id, String password);

}
