package com.cg.boot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Course;
import com.cg.boot.repository.CourseRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

/**
 * 
 * @author Madhuri
 *
 */
@SpringBootTest
class CourseServiceTest {
	@Autowired
	ICourseService service;

	@MockBean
	CourseRepository repository;

	/**
	 * This method test getAllCourses() using Mockito and also specify the condition
	 * and if the size of course list is exactly equal to given size in the function
	 * then test will pass.
	 * 
	 * @param list : {@link List}
	 */

	@Test
	public void getAllCourseTest() {
		when(repository.findAll())
				.thenReturn(Stream.of(new Course(21, "Java", 2500.0, 30, 6)).collect(Collectors.toList()));
		assertEquals(1, service.getAllCourses().size());
	}

	/**
	 * This method test addCourse() using Mockito and also specify the condition.
	 * Check that the data is added or not.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void addCourseTest() {
		Course course = new Course(21, "Java", 2500.0, 30, 6);
		when(repository.save(course)).thenReturn(course);
		assertEquals(course, service.addCourse(course));
	}

	/**
	 * This method test updateCourse() method using Mockito and also specify the
	 * condition. Check whether data is updated successfully or not.
	 * 
	 * @param value : {@link Value}
	 */
    
//	@Test
//	public void updateCourseTest() {
//		Course course = new Course(21, "Java", 2500.0, 30, 6);
//		when(repository.save(course)).thenReturn(course);
//		assertNotEquals(course, service.updateCourse(course));
//	}

	/**
	 * This method test deleteById() method using Mockito and also specify the
	 * condition. Check whether the data is deleted or not.
	 * 
	 * @param value : {@link Value}
	 */

//	@Test
//	public void deleteCourseTest() {
//		Course course = new Course(21, "Java", 2500.0, 30, 6);
//		service.deleteCourse(course.getCourseId(), course.getAdminId());
//		verify(repository, times(1)).deleteById(course.getCourseId());
//
//	}

}
