package com.cg.boot.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

/**
 * @author Nilima
 *
 */
@RestController
@RequestMapping("/api")
public class StudentProgressDetailsController {

	@Autowired
	IProgressDetailsService service;
	Logger logger=LoggerFactory.getLogger(StudentProgressDetailsController.class);
	
	/**
	 * This method accepts grade which user has inserted. Return response entity
	 * containing list of progress details .
	 * 
	 * @param grade
	 * @return {@link ResponseEntity} grades {@link List} {@link HttpStatus}
	 * @throws DataNotFoundException
	 */
	@GetMapping("/getStudentProgressDetails/{grade}")
	public ResponseEntity<List<ProgressDetails>> getGrade(@PathVariable String grade){
		// List<Grade>list=null;
		List<ProgressDetails> grades = service.getAllProgressDetails(grade);
		if (grades.isEmpty()) {
			logger.error("Progress Details Not Found by Grade"+grade);
			throw new DataNotFoundException("Progress Details Not Found");
		}
		logger.info("Progress Details Return successfully with Grade "+grade);
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}
	
	/**
	 * This method accepts grade Id. Return response entity containing progress
	 * details based on grade Id.
	 * 
	 * @param gradeId {@link Integer}
	 * @return
	 * @throws DataNotFoundException
	 */
	@GetMapping("/getStudentProgressDetailsById/{gradeId}")
	public ResponseEntity<ProgressDetails> getProgressDetails(@PathVariable("gradeId") int gradeId){
		ProgressDetails grade = service.getProgressDetails(gradeId);
		if (grade == null) {
			logger.warn("Grade Not Found By ID"+gradeId);
			throw new DataNotFoundException("Grade Not Found By these ID");
		}
		logger.info("Return Grade Details Successfully by ID "+gradeId);
		return new ResponseEntity<ProgressDetails>(grade, HttpStatus.OK);
	}
/*
 * Get All ProgressDetails
 */
	@GetMapping("/getAllStudentProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades(){
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			logger.warn("Grades Details Not Found");
			throw new DataNotFoundException("Grades Details Not Found");
		}
		logger.info("All Grade details Return successfully");
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
