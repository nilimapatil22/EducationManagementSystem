package com.cg.boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.Course;
/**
 * @author Madhuri. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class Course that is being
 *         managed.
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{
    
	Course findByCourseName(String courseName);
	

}
