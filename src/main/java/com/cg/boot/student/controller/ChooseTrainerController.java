package com.cg.boot.student.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ChooseCourse;
import com.cg.boot.model.ChooseTrainer;
import com.cg.boot.model.Trainer;
import com.cg.boot.service.ITrainerService;

/**
 * 
 * @author Madhuri
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ChooseTrainerController {

	@Autowired
	ITrainerService service;
	Logger logger = LoggerFactory.getLogger(ChooseTrainerController.class);

	/**
	 * The given method returns the trainer list which are choose by student. If
	 * given trainer id is not present then returns trainer not found.
	 * 
	 * @throws DataNotFoundException
	 * @param trainerId : {@link Integer}
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity} : {@link Trainer}, {@link HttpStatus}
	 * @author Madhuri
	 */
	@GetMapping("/getChoosedTrainer/{trainerId}/{studentId}")
	public ResponseEntity<ChooseTrainer> getTrainerDetails(@PathVariable("trainerId") int trainerId,
			@PathVariable("studentId") int studentId) {
		ChooseTrainer trainer = service.getTrainerDetails(trainerId, studentId);
		if (trainer == null) {
			logger.error("Course Trainer not Choosen");
			throw new DataNotFoundException("Trainer Not Found By these ID");
		}
		logger.info("Course trainer Choose Successfully");
		return new ResponseEntity<ChooseTrainer>(trainer, HttpStatus.OK);
	}
	
	@PostMapping("/ChooseTrainer/{studentId}")
	public ResponseEntity<Integer> chooseTrainer(@PathVariable("studentId") int studentId,
			@Valid @RequestBody ChooseTrainer choosetrainer) {
		ChooseTrainer trainer = service.chooseTrainerDetails(studentId,choosetrainer);
		logger.info("Course trainer Choose Successfully");
		return new ResponseEntity<Integer>(trainer.getTrainerId(), HttpStatus.OK);
		
	}
	
}
