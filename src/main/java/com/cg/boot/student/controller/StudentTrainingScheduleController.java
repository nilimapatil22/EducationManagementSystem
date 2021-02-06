package com.cg.boot.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.service.ITrainingScheduleService;

/**
 * @author Priyanka
 *
 */

@RestController
public class StudentTrainingScheduleController {
	@Autowired
	ITrainingScheduleService scheduleService;
	Logger logger = LoggerFactory.getLogger(StudentTrainingScheduleController.class);

	/**
	 * This method accepts schedule Id which user has inserted. Return response
	 * entity containing training schedule details based on schedule Id.
	 * 
	 * @param scheduleId : {@link Integer}
	 * @return {@link ResponseEntity}: trainingSchedule {@link TrainingSchedule},
	 *         {@link HttpStatus}
	 */
	@GetMapping("/getStudentSchedule/{scheduleId}")
	public ResponseEntity<TrainingSchedule> getSchedule(@PathVariable("scheduleId") int scheduleId) {
		TrainingSchedule trainingSchedule = scheduleService.getSchedule(scheduleId);
		if (trainingSchedule == null) {
			logger.error("No schedule present with given id: " + scheduleId);
			throw new DataNotFoundException("No schedule present with given id: " + scheduleId);
		}
		logger.info("Training schedule return successfully");
		return new ResponseEntity<TrainingSchedule>(trainingSchedule, HttpStatus.OK);

	}

	/**
	 * This method accepts student Id which user has inserted. Return response
	 * entity containing list of training schedule details based on student Id.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity}: trainingSchedulesList {@link List},
	 *         {@link HttpStatus}
	 */
	@GetMapping("/getStudentScheduleByStudentId/{studentId}")
	public ResponseEntity<List<TrainingSchedule>> getScheduleByStudentId(@PathVariable("studentId") int studentId) {
		List<TrainingSchedule> trainingSchedules = scheduleService.getScheduleByStudentId(studentId);
		logger.info("Training schedule return successfully with ID " + studentId);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedules, HttpStatus.OK);
	}
}
