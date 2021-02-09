package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;

/**
 * 
 * @author Prajakta
 *
 */
@SpringBootTest
class UserServiceTest {

	@Autowired
	IUserService service;

	@MockBean
	UserRepository repository;

	UserService listMock = mock(UserService.class, "myMock");

	/**
	 * This method test getAllusersTest( ) method using Mockito. and also specified
	 * the condition Check the size of user details we added. Accordingly test case
	 * fails or pass
	 * 
	 * @param {@link List}
	 */
	@Test
	public void getAllUsersTest() {
		when(repository.findAll()).thenReturn(
				Stream.of(new User(2, "Kiran", "Sony", "kiran@gmail.com", "Kiran@123", 9890851226l, "Jammu", "student"))
						.collect(Collectors.toList()));
		assertEquals(1, service.getAllUsers().size());
	}

	/**
	 * This method test updateUserDetailsStudent( ) method using Mockito and also
	 * specified condition. Check whether user is null or not null
	 * 
	 * @param value {@link value}
	 */
	@Test
	public void updateUserDetailsStudentTest() {
		User user = new User(26, "Priyanka", "Pawar", "pawar@gmail.com", "Priyanka@123", 9090891223l, "Baramati",
				"student");
		when(repository.save(user)).thenReturn(user);
		assertNull(user);
	}

	/**
	 * This method test updateUserDetailsAdmin( ) method using Mockito and also
	 * specified condition. Check whether the class is updated successfully or not.
	 * 
	 * @param value {@link value}
	 */
	@Test
	public void updateUserDetailsAdminTest() {
		User user = new User(25, "	Prajakta", "Girase", "prajakta@gmail.com", "Prajakta@123", 9090891223l, "Amravati",
				"admin");
		when(repository.save(user)).thenReturn(user);
		assertNull(user);
	}

	/**
	 * This method test addUserDetailsStudent( ) method using Mockito and also
	 * specified condition. Check whether the class is null or not.
	 * 
	 * @param value {@link value}
	 */

	@Test
	public void addUserDetailsStudentTest() {
		User user = new User(23, "Tina", "Khan", "tinakhan@gmail.com", "tina@123", 8990090987l, "Chandrapur",
				"student");
		when(repository.save(user)).thenReturn(user);
		assertNotNull(user);
	}

	/**
	 * This method test addUserDetailsAdmin( ) method using Mockito and also
	 * specified condition. Check whether the class is null or not.
	 * 
	 * @param value {@link value}
	 */
	@Test
	public void addUserDetailsAdminTest() {
		User user = new User(23, "Tina", "Khan", "tinakhan@gmail.com", "tina@123", 8990090987l, "Chandrapur",
				"student");
		when(repository.save(user)).thenReturn(user);
		assertNotNull(user);
	}

	/**
	 * This method test deleteUserDetails( ) method using Mockito and also specified
	 * condition. Checks whether it is deleted or not.
	 * 
	 * @param value {@link value}
	 */

	@Test
	public void deleteUserDetailsTest() {
		User user = new User(23, "Tina", "Khan", "tinakhan@gmail.com", "tina@123", 8990090987l, "Chandrapur",
				"student");
		service.deleteUser(user.getUserId());
		verify(repository, times(1)).deleteById(user.getUserId());
	}

	/**
	 * This method test validatePhoneNumber( ) method and specify condition.
	 * Validate phone number. Return true or false based on condition.
	 * 
	 * @param value {@link Value}
	 */

	@Test
	public void validatePhoneNumber() {
		when(listMock.validatePhoneNumber(9890877612l)).thenReturn(true);
		boolean phoneFlag = listMock.validatePhoneNumber(9890851226l);
		verify(listMock).validatePhoneNumber(9890851226l);
		assertThat(phoneFlag);
	}

	/**
	 * This method test validateRoleType( ) method and specify condition. Validate
	 * role type. Return true or false based on condition.
	 * 
	 * @param value {@link Value}
	 */

	@Test
	public void validateRoleTypeTest() {
		when(listMock.validateRoleType("admin")).thenReturn(false);
		boolean roleFlag = listMock.validateRoleType("admin");
		verify(listMock).validateRoleType("student");
		assertThat(roleFlag);
	}

	/**
	 * This method test validatePassword( ) method and specify condition. Validate
	 * password. Return true or false based on condition.
	 * 
	 * @param value {@link Value}
	 */
	@Test
	public void validatePasswordTest() {
		when(listMock.validatePassword("Kavita@123 ")).thenReturn(false);
		boolean passFlag = listMock.validatePassword("Sarita@123");
		verify(listMock).validatePassword("Sarita@123");
		assertThat(passFlag);
	}

	/**
	 * This method test validateAdminId( ) method and specify condition. Validate
	 * admin id. Return true or false based on condition.
	 * 
	 * @param value {@link Value}
	 */
	@Test
	public void validateAdminIdTest() {
		when(listMock.validateAdminId(10)).thenReturn(true);
		boolean adminFlag = listMock.validateAdminId(10);
		verify(listMock).validateAdminId(9);
		assertThat(adminFlag);
	}

	/**
	 * This method test validateStudentId( ) method and specify condition. Validate
	 * student id. Return true or false based on condition.
	 * 
	 * @param value {@link Value}
	 */

	@Test
	public void validateStudentIdTest() {
		when(listMock.validateStudentId(8)).thenReturn(true);
		boolean studentFlag = listMock.validateStudentId(8);
		verify(listMock).validateStudentId(8);
		assertThat(studentFlag);
	}

	/**
	 * This method test getUser( ) method and specify the condition Check whether
	 * userId is equal or not
	 * 
	 * @param value  {@link Value}
	 * @param userId
	 */
	@Test
	public void getUserTest() {
		int userId = 2;
		List<User> userList = Stream.of(
				new User(2, "Kiran", "Sony", "kiran@gmail.com", "Kiran@123", 9890851226l, "Jammu", "student"),
				new User(23, "Samita", "Gawande", "samita@gmail.com", "Samita@123", 7709920871l, "Nagpur", "student"))
				.collect(Collectors.toList());
		repository.saveAll(userList);
		when(repository.findById(userId)).thenReturn(Optional.of(userList.get(1)));
		assertEquals(userList.get(1), service.getUser(userId));
	}

}
