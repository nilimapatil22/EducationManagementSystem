package com.cg.boot.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ChooseCourse;
import com.cg.boot.model.Course;
import com.cg.boot.model.Payment;
import com.cg.boot.service.ICourseService;

/**
 * 
 * @author Madhuri
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ChooseCourseController {
	@Autowired
	ICourseService courseService;
	Logger logger = LoggerFactory.getLogger(ChooseCourseController.class);

	/**
	 * This method represents which course is selected by student using their id. If
	 * course Id is not present in the database it returns course details are not
	 * found.
	 * 
	 * @throws DataNotFoundException
	 * @param courseId  : {@link Integer}
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity} : Course {@link Course}, {@link HttpStatus}
	 * @author Madhuri
	 */

	
	@GetMapping("/chooseCourse/{courseId}/{studentId}")
	public ResponseEntity<ChooseCourse> chooseCourse(@PathVariable("courseId") int courseId,
			@PathVariable("studentId") int studentId) {
		ChooseCourse course = courseService.getChoosedCourseDetails(courseId, studentId);
		if (course == null) {
			logger.error("Course not Choosen");
			throw new DataNotFoundException("Course Details are not found");
		}
		logger.info("Course Choose Successfully");
		return new ResponseEntity<ChooseCourse>(course, HttpStatus.OK);

	}
	@PostMapping("/enrollcourse/{studentId}")
	public ResponseEntity<Integer> enrollCourse(@PathVariable("studentId") int studentId,@Valid @RequestBody ChooseCourse course) throws DataNotFoundException {
		ChooseCourse courseInfo = courseService.enrollCourse(studentId,course);
		logger.info("course enroll sucessfully");
		return new ResponseEntity<Integer>(courseInfo.getCourseId(), HttpStatus.OK);
	}
	/**
	 * This method return course list which are choosen by student.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity} : ChooseCourse {@link List},
	 *         {@link HttpStatus}
	 * @author Madhuri
	 */

	@GetMapping("/getChoosedCoursesByStudentId/{studentId}")
	public ResponseEntity<List<ChooseCourse>> getChoosedCoursesByStudentId(@PathVariable("studentId") int studentId) {
		List<ChooseCourse> chooseCourses = courseService.getChoosedCoursesByStudentId(studentId);
		logger.info("Course Details return Successfully by Student ID " + studentId);
		return new ResponseEntity<List<ChooseCourse>>(chooseCourses, HttpStatus.OK);

	}

}
