package com.cg.boot.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Course;
import com.cg.boot.service.ICourseService;

/**
 * @author nilima
 *
 */
@RestController
@RequestMapping
public class StudentCourseController {
	@Autowired
	ICourseService service;
	Logger logger=LoggerFactory.getLogger(StudentCourseController.class);
	/**
	 * This method accepts course id through URl and return the course details of that 
	 * particular course Id.and return an object of course containing all arguments which has been saved.
	 * @param id {@link Integer}
	 * @return "Course" {@link Course}
	 */
	
	@GetMapping("/getStudentCourse/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable("id") int id){

		Course getCourse = service.getCourse(id);
		if (getCourse == null) {
			logger.warn("Student course Details not found with ID "+id);
			throw new DataNotFoundException("No Course present with the given id ");
		}
		logger.warn("Student course Details found successfully with ID "+id);
		return new ResponseEntity<Course>(getCourse, HttpStatus.OK);
	}
	/**
	 * This method returns list of all courses.
	 * @param course : {@link Course}
	 * @return {@link ResponseEntity}: course {@link Course}, {@link HttpStatus}
	 */
	@GetMapping("/getAllStudentCourses")
	public ResponseEntity<List<Course>> getCourses() throws Exception {
		List<Course> getCourses = service.getAllCourses();
		logger.warn("Student course Details found successfully");
		return new ResponseEntity<List<Course>>(getCourses, HttpStatus.OK);
	}
	
	

}
