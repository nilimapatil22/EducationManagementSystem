package com.cg.boot.admin.controller;

import java.util.List;


import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Course;
import com.cg.boot.service.ICourseService;
@RestController
@RequestMapping
public class CourseController {
	@Autowired
	ICourseService service;
	Logger logger=LoggerFactory.getLogger(CourseController.class);
	@PostMapping("/addCourse")
	public Course addCourse(@Valid @RequestBody Course course) {
		Course addCourse = service.addCourse(course);
		logger.info("Course Added Successfully");
				return addCourse;
	}

	@GetMapping("/getCourse/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable("id") int id) {

		Course getCourse = service.getCourse(id);
		if (getCourse == null) {
			logger.warn("Course Id Not Found");
			throw new DataNotFoundException("No Course present with the given id ");
		}
		logger.info("Course Details Found");
		return new ResponseEntity<Course>(getCourse, HttpStatus.OK);
	}

	@GetMapping("/getAllCourses")
	public ResponseEntity<List<Course>> getCourses() throws Exception {
		List<Course> getCourses = service.getAllCourses();
		logger.info("Course Details Found");
		return new ResponseEntity<List<Course>>(getCourses, HttpStatus.OK);
	}

	@PutMapping("/updateCourse")
	public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course) {
		Course updateCourse = service.updateCourse(course);
		if (updateCourse == null) {
			logger.warn("Course Details Not Found to update");
			throw new DataNotFoundException("Course Details Not Found for update");
		}
		logger.info("Course Details Updated successfully");
		return new ResponseEntity<Course>(updateCourse, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCourse/{courseId}/{userId}")
	public ResponseEntity<List<Course>> deleteCourse(@PathVariable("courseId") int courseId,
			@PathVariable("userId") int userId) {
		List<Course> course = service.deleteCourse(courseId,userId);
		if (course == null) {
			logger.warn("Course Details Not Found To Delete");
			throw new DataNotFoundException("No Course present with the given id: " + courseId);
		}
		logger.info("Course Details with  ID "+courseId+" Deleted Successfully");
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}

}
