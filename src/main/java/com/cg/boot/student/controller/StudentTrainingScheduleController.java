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

@RestController
public class StudentTrainingScheduleController {
	@Autowired
	ITrainingScheduleService scheduleService;
	Logger logger=LoggerFactory.getLogger(StudentTrainingScheduleController.class);
	/*
	 * Get schedule based on schedule id
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
	
	/*
	 * Get schedules based on student id
	 */
	@GetMapping("/getStudentScheduleByStudentId/{studentId}")
	public ResponseEntity<List<TrainingSchedule>> getScheduleByStudentId(@PathVariable("studentId") int studentId) {
		List<TrainingSchedule> trainingSchedules = scheduleService.getScheduleByStudentId(studentId);
		logger.info("Training schedule return successfully with ID "+studentId);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedules, HttpStatus.OK);
	}
}
