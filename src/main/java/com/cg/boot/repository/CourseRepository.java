package com.cg.boot.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.Course;

public interface CourseRepository extends JpaRepository<Course,Integer>{
    
	public Course findByCourseName(String courseName);
	
	public List<Course> findAllByStudentId(int id);

}
