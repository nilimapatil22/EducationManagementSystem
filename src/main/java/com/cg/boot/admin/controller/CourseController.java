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


/**
 * 
 * @author Madhuri
 *
 */

@RestController
@RequestMapping
public class CourseController {
	@Autowired
	ICourseService courseService;
	Logger logger=LoggerFactory.getLogger(CourseController.class);
	
	/**
	 * This method accepts and saves courses which user has inserted through object.
	 * Return an object of course containing all arguments which has been saved.
	 * 
	 * @param course : {@link Course}
	 * @return Course : {@link Course}
	 */

	@PostMapping("/addCourse")
	public Course addCourse(@Valid @RequestBody Course course) {
		Course addCourse = courseService.addCourse(course);
		logger.info("Course Added Successfully");
				return addCourse;
	}
	
	/**
	 * This method accepts course Id which user has inserted. Return response entity
	 * containing course details based on course Id.
	 * 
	 * @param id : {@link Integer}
	 * @return {@link ResponseEntity}:course {@link Course}, {@link HttpStatus}
	 */


	@GetMapping("/getCourse/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable("id") int id) {

		Course getCourse = courseService.getCourse(id);
		if (getCourse == null) {
			logger.warn("Course Id Not Found");
			throw new DataNotFoundException("No Course present with the given id ");
		}
		logger.info("Course Details Found");
		return new ResponseEntity<Course>(getCourse, HttpStatus.OK);
	}
	
	/**
	 * This method returns list of all courses.
	 * 
	 * @return {@link ResponseEntity}: courseList {@link List}, {@link HttpStatus}
	 */


	@GetMapping("/getAllCourses")
	public ResponseEntity<List<Course>> getCourses() throws Exception {
		List<Course> getCourses = courseService.getAllCourses();
		logger.info("Course Details Found");
		return new ResponseEntity<List<Course>>(getCourses, HttpStatus.OK);
	}
    

	/**
	 * This method accepts student Id which user has inserted. Return response
	 * entity containing list of course details based on student Id.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity}: messageList {@link List}, {@link HttpStatus}
	 */
	@GetMapping("/getCourseByStudentId")
	public ResponseEntity<List<Course>> getcoursesByStudentId(@PathVariable("studentId") int studentId) {
		List<Course> courseList = courseService.getCoursesByStudentId(studentId);
		logger.info("Message Details Found with Student ID "+studentId);
		return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
	}
	
	/**
	 * This method accepts and update courses which user has inserted through
	 * object. Return response entity containing details of course which has been
	 * updated.
	 * 
	 * @param course : {@link Course}
	 * @return {@link ResponseEntity}: course {@link Course}, {@link HttpStatus}
	 */

	@PutMapping("/updateCourse")
	public ResponseEntity<Course> updateCourse(@Valid @RequestBody Course course) {
		Course updateCourse = courseService.updateCourse(course);
		if (updateCourse == null) {
			logger.warn("Course Details Not Found to update");
			throw new DataNotFoundException("Course Details Not Found for update");
		}
		logger.info("Course Details Updated successfully");
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

	@DeleteMapping("/deleteCourse/{courseId}/{userId}")
	public ResponseEntity<List<Course>> deleteCourse(@PathVariable("courseId") int courseId,
			@PathVariable("userId") int userId) {
		List<Course> course = courseService.deleteCourse(courseId,userId);
		if (course == null) {
			logger.warn("Course Details Not Found To Delete");
			throw new DataNotFoundException("No Course present with the given id: " + courseId);
		}
		logger.info("Course Details with  ID "+courseId+" Deleted Successfully");
		return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
	}

}
