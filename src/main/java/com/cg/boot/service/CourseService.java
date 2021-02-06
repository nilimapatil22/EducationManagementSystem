package com.cg.boot.service;

import java.util.List;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ChooseCourse;
import com.cg.boot.model.Course;
import com.cg.boot.repository.ChooseCourseRepository;
import com.cg.boot.repository.CourseRepository;

/**
 * 
 * @author Madhuri
 *
 */

@Service
@Transactional
public class CourseService implements ICourseService {
	@Autowired
	CourseRepository repository;
	@Autowired
	ChooseCourseRepository chooseCourseRepository;
	@Autowired
	UserService userService;
	Logger logger = LoggerFactory.getLogger(CourseService.class);
    
	/**
	 * This method accepts and saves courses which user has inserted through object.
	 * Return an object of course containing all arguments which has been saved.
	 * 
	 * @param course:{@link Course}
	 * @return Course:{@link Course}
	 */

	@Override
	public Course addCourse(Course course) {
		userService.validateAdminId(course.getAdminId());
		return repository.save(course);
	}
	
	/**
	 * This method accepts course Id which user has inserted. Return response entity
	 * containing course details based on course Id.
	 * 
	 * @param courseId : {@link Course}
	 * @return {@link ResponseEntity}:course {@link Course}, {@link HttpStatus}
	 * 
	 */

	@Override
	public Course getCourse(int id) {
		return repository.findById(id).orElse(null);
	}
    
	/**
	 * This method returns list of all courses.
	 * 
	 * @return {@link ResponseEntity}: courseList {@link List}, {@link HttpStatus}
	 */

	@Override
	public List<Course> getAllCourses() {
		return repository.findAll();
	}
    
	/**
	 * This method accepts and update courses which user has inserted through
	 * object. Return response entity containing details of course which has been
	 * updated.
	 * 
	 * @param course : {@link Course}
	 * @return {@link ResponseEntity}: course {@link Course}, {@link HttpStatus}
	 */

	@Override
	public Course updateCourse(Course course) {
		userService.validateAdminId(course.getAdminId());
		Course courseDetails = getCourse(course.getCourseId());
		if (courseDetails != null) {
			logger.warn("Course Details not found for update");
			courseDetails = repository.save(course);
		}
		return courseDetails;
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

	@Override
	public List<Course> deleteCourse(int courseId, int userId) {
		userService.validateAdminId(userId);
		repository.deleteById(courseId);
		return repository.findAll();
	}
    
	
	/**
	 * This method validate the course. Check name of passed course. Return
	 * true or false based on condition.
	 * 
	 * @param course     : {@link String}
	 * @param courseName :{@link String}
	 * @return flag : {@link Boolean}
	 */

	public boolean validateCourse(String courseName) {
		Course course = repository.findByCourseName(courseName);
		if (course == null) {
			throw new DataNotFoundException("Course is not valid");
		}
		return true;

	}
    
	/**
	 * This method performs choose Course Details. Check if selected course is present
	 * in database or not. Return course name and Id based on student Id.
	 * 
	 * @param courseId  : {@link Integer}
	 * @param studentId : {@link Integer}
	 */

	@Override
	public ChooseCourse getChoosedCourseDetails(int courseId, int studentId) {
		userService.validateStudentId(studentId);
		ChooseCourse chooseCourse = null;
		Course course = repository.findById(courseId).orElse(null);
		if (course != null) {
			logger.warn("Course Details not found for Choose Course");
			chooseCourse = new ChooseCourse(studentId, courseId, course.getCourseName());
		}
		chooseCourseRepository.save(chooseCourse);

		return chooseCourse;
	}
    
	/**
	 * This method finds courses by passed student Id. Returns list of courses based
	 * on student Id. Check whether list of courses is empty or not.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link List}
	 */

	@Override
	public List<ChooseCourse> getChoosedCoursesByStudentId(int studentId) {
		List<ChooseCourse> chooseCourses = chooseCourseRepository.findAllByStudentId(studentId);
		if (chooseCourses.isEmpty()) {
			logger.warn("Course Details not found for ID "+studentId);
			throw new DataNotFoundException("Choosed Courses not found");
		}
		return chooseCourses;
	}
    
	

	

}
