package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{
    
	Course findByCourseName(String courseName);
	

}
