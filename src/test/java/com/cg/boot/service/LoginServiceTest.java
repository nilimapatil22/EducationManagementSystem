package com.cg.boot.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;

/**
 * 
 * @author Prajkta
 *
 */
@SpringBootTest
class LoginServiceTest {
	@Autowired
	ILoginService service;
	
	@MockBean
	UserRepository repository;
	
	LoginService listMock=mock(LoginService.class,"myMock");
	
	/**
	 * This method test getAdminLogin() method using Mockito.
	 * check that data is equal or not with the given admin data.
	 * 
	 * @param adminId : {@link Integer}
	 * @param password : {@link String}
	 */
	@Test
	public void getAdminLoginTest() {
		User user= new User(8,"Ishar@123");
		when(listMock.getAdminLogin(8, "Ishar@123")).thenReturn(user);
		assertEquals(user, listMock.getAdminLogin(8, "Ishar@123"));
	}
	
	/**
	 * This method test getStudentLoginTest() method using Mockito.
	 * check that the data is equal or not with the given student data.
	 * 
	 * @param studentId : {@link Integer}
	 * @param password : {@link String}
	 */
	@Test
	public void getStudentLoginTest() {
		User user= new User(8,"Ishar@123");
		when(listMock.getStudentLogin(8, "Ishar@123")).thenReturn(user);
		assertEquals(user, listMock.getStudentLogin(8, "Ishar@123"));
	}
	
	

}