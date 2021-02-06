package com.cg.boot.admin.controller;

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
import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.service.IProgressDetailsService;

@RestController
@RequestMapping("/api")
public class PreviousProgressDetailsController {
	@Autowired
	IProgressDetailsService service;
	Logger logger=LoggerFactory.getLogger(PreviousProgressDetailsController.class);
/*
 * Get ProgressDetails By Id
 */
	@GetMapping("/getPreviousProgressDetailsById/{studentId}")
	public ResponseEntity<List<PreviousProgressDetails>> getProgressDetails(@PathVariable("studentId") int studentId) throws DataNotFoundException {
		List<PreviousProgressDetails> list = service.getAllProgressDetailsByStudentId(studentId);
		if (list == null) {
			logger.warn("Previous Progress Details Not found By ID "+studentId);
			throw new DataNotFoundException("Student Grade Not Found By these ID");
		}
		logger.info("Previous Progress Details return Successfully with student ID "+studentId);
		return new ResponseEntity<List<PreviousProgressDetails>>(list,HttpStatus.OK);
	}
/*
 * Get All ProgressDetails
 */
	@GetMapping("/getAllPreviousProgressDetails")
	public ResponseEntity<List<ProgressDetails>> getGrades() throws DataNotFoundException {
		List<ProgressDetails> grades = service.getAllProgressDetails();
		if (grades.isEmpty()) {
			logger.warn("Previous Progress Details Not found");
			throw new DataNotFoundException("Grades Details Not Found");
		}
		logger.info("All Previous Progress Details found");
		return new ResponseEntity<List<ProgressDetails>>(grades, HttpStatus.OK);
	}

}
