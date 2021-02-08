package com.cg.boot.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{
    
	Course findByCourseName(String courseName);
<<<<<<< HEAD

	//public List<Course> findAllByStudentId(int studentId);
=======
>>>>>>> fc65bf716ce91ead01d9c92c10d30ccdf1d311d5
	

}
