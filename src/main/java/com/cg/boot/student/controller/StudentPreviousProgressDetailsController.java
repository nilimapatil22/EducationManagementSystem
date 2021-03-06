package com.cg.boot.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

/**
 * @author Nilima
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentPreviousProgressDetailsController {
	@Autowired
	IProgressDetailsService service;
	Logger logger = LoggerFactory.getLogger(StudentPreviousProgressDetailsController.class);

	/**
	 * This method accepts progress details based on passed object and return
	 * Response Entity containing list of previous progress details.
	 * 
	 * @param studentId
	 * @return {@link ResponseEntity} list {@link List}{@link HttpStatus}
	 * @throws DataNotFoundException
	 */
	
	@GetMapping("/getStudentPreviousProgressDetailsById/{studentId}")
	public ResponseEntity<List<PreviousProgressDetails>> getProgressDetails(@PathVariable("studentId") int studentId)
			throws DataNotFoundException {
		List<PreviousProgressDetails> list = service.getAllProgressDetailsByStudentId(studentId);
		if (list == null) {
			logger.warn("student previous progress Details not found By student ID " + studentId);
			throw new DataNotFoundException("Student Grade Not Found By these ID");
		}
		logger.info("student previous progress Details return successfully By student ID " + studentId);
		return new ResponseEntity<List<PreviousProgressDetails>>(list, HttpStatus.OK);
	}

	/**
	 * This method return list of all previous progress details
	 * 
	 * @return {@link ResponseEntity}grades {@link List} {@link HttpStatus}
	 * @throws DataNotFoundException
	 */
	
	@GetMapping("/getStudentAllPreviousProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades() throws DataNotFoundException {
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			logger.warn("student previous progress Details not found");
			throw new DataNotFoundException("Grades Details Not Found");
		}
		logger.info("student previous progress Details return successfully");
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
