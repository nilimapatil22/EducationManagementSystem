package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

/**
 * 
 * @author Madhuri
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CourseController {
	@Autowired
	ICourseService service;

	/**
	 * This method accepts and saves courses which user has inserted through object.
	 * Return an object of course containing all arguments which has been saved.
	 * 
	 * @param course : {@link Course}
	 * @return Course : {@link Course}
	 */
	
	//http://localhost:7171/api/Course
	@PostMapping("/Course")
	public Course addCourse(@Valid @RequestBody Course course) {
		Course addCourse = service.addCourse(course);
		return addCourse;
	}

	/**
	 * This method accepts course Id which user has inserted. Return response entity
	 * containing course details based on course Id.
	 * 
	 * @param id : {@link Integer}
	 * @return {@link ResponseEntity}:course {@link Course}, {@link HttpStatus}
	 */
	
	//http://localhost:7171/api/getCourse/6
	@GetMapping("/getCourse/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable("id") int id) {

		Course getCourse = service.getCourse(id);
		if (getCourse == null) {
			throw new DataNotFoundException("No Course present with the given id ");
		}
		return new ResponseEntity<Course>(getCourse, HttpStatus.OK);
	}

	/**
	 * This method returns list of all courses.
	 * 
	 * @return {@link ResponseEntity}: courseList {@link List}, {@link HttpStatus}
	 */
	
	//http://localhost:7171/api/getAllCourses
	@GetMapping("/getAllCourses")
	public ResponseEntity<List<Course>> getCourses() {
		List<Course> getCourses = service.getAllCourses();
		return new ResponseEntity<List<Course>>(getCourses, HttpStatus.OK);
	}

	/**
	 * This method accepts and update courses which user has inserted through
	 * object. Return response entity containing details of course which has been
	 * updated.
	 * 
	 * @param course : {@link Course}
	 * @return {@link ResponseEntity}: course {@link Course}, {@link HttpStatus}
	 */
	//http://localhost:7171/api/updateCourse
	@PutMapping("/updateCourse/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course course) {
		Course updateCourse = service.updateCourse(courseId, course);
		if (updateCourse == null) {
			throw new DataNotFoundException("Course Details Not Found for update");
		}
		return new ResponseEntity<Course>(updateCourse, HttpStatus.OK);
	}

	/**
	 * This method accepts course Id to delete course based on course Id. Accepts
	 * user Id to check authorized user to perform operation. Return list of
	 * remaining courses except deleted one
	 * 
	 * @param courseId : {@link Integer}
	 * @param userId   : {@link Integer}
	 * @return {@link ResponseEntity}: coursesList {@link List}, {@link HttpStatus}
	 */
	//http://localhost:7171/api/deleteCourse/6
	@DeleteMapping("/deleteCourse/{courseId}")
	public ResponseEntity<List<Course>> deleteCourse(@PathVariable("courseId") int courseId) {
		List<Course> course = service.deleteCourse(courseId);
		if (course == null) {
			throw new DataNotFoundException("No Course present with the given id: " + courseId);
		}
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}

}
