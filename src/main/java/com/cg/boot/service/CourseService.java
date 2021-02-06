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

	@Override
	public Course addCourse(Course course) {
		userService.validateAdminId(course.getAdminId());
		return repository.save(course);
	}

	@Override
	public Course getCourse(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Course> getAllCourses() {
		return repository.findAll();
	}

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

	@Override
	public List<Course> deleteCourse(int courseId, int userId) {
		userService.validateAdminId(userId);
		repository.deleteById(courseId);
		return repository.findAll();
	}

	public boolean validateCourse(String courseName) {
		Course course = repository.findByCourseName(courseName);
		if (course == null) {
			throw new DataNotFoundException("Course is not valid");
		}
		return true;

	}

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
