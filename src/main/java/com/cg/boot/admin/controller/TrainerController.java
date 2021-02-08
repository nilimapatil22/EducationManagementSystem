package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Trainer;
import com.cg.boot.service.ITrainerService;

/**
 * 
 * @author Madhuri
 *
 */

@RestController
@RequestMapping("/api")
public class TrainerController {
	@Autowired
	ITrainerService trainerService;
	Logger logger = LoggerFactory.getLogger(TrainerController.class);

	/**
	 * This method accepts and saves trainer which user has inserted through object.
	 * Return an object of trainer containing all arguments which has been saved.
	 * 
	 * @param trainer : {@link Trainer}
	 * @return trainer : {@link Trainer}
	 */

	@PostMapping("/addTrainer")
	public Trainer addTrainer(@Valid @RequestBody Trainer trainer) {
		Trainer addTrainer = trainerService.addTrainer(trainer);
		logger.info("Trainer Added Successfully");
		return addTrainer;
	}

	/**
	 * This method accepts trainer Id which user has inserted. Return response
	 * entity containing trainer details based on trainer Id.
	 * 
	 * @throws DataNotFoundException
	 * @param id : {@link Integer}
	 * @return {@link ResponseEntity} : trainer {@link Trainer}, {@link HttpStatus}
	 */

	@GetMapping("/getTrainer/{id}")
	public ResponseEntity<Trainer> getTrainer(@PathVariable("id") int id) {

		Trainer getTrainer = trainerService.getTrainer(id);
		if (getTrainer == null) {
			logger.warn("Trainer deatils not found with Id " + id);
			throw new DataNotFoundException("No Trainer present with the given id: " + id);
		}
		logger.info("Trainer return  Successfully with Id " + id);
		return new ResponseEntity<Trainer>(getTrainer, HttpStatus.OK);
	}

	/**
	 * This method returns list of all trainers.
	 * 
	 * @return {@link ResponseEntity}: trainerList {@link List}, {@link HttpStatus}
	 *
	 */

	@GetMapping("/getAllTrainers")
	public ResponseEntity<List<Trainer>> getTrainers() throws Exception {
		List<Trainer> getTrainers = trainerService.getAllTrainers();
		logger.info("All Trainer Details  Successfully ");
		return new ResponseEntity<List<Trainer>>(getTrainers, HttpStatus.OK);
	}

	/**
	 * This method accepts and update courses which user has inserted through
	 * object. Return response entity containing details of course which has been
	 * updated.
	 * 
	 * @param trainer : {@link Trainer}
	 * @return {@link ResponseEntity}: trainer {@link Trainer}, {@link HttpStatus}
	 */

	@PutMapping("/updateTrainer")
	public ResponseEntity<Trainer> updateTrainer(@Valid @RequestBody Trainer trainer) {
		Trainer updateTrainer = trainerService.updateTrainer(trainer);
		logger.info("All Trainer Details Updated Successfully ");
		return new ResponseEntity<Trainer>(updateTrainer, HttpStatus.OK);
	}

	/**
	 * This method accepts trainer Id to delete trainer details based on trainer Id.
	 * Accepts user Id to check authorized user to perform operation. Return list of
	 * remaining trainers except deleted one.
	 * 
	 * @throws DataNotFoundException
	 * @param trainerId : {@link Integer}
	 * @param userId    : {@link Integer}
	 * @return {@link ResponseEntity}: trainer {@link List}, {@link HttpStatus}
	 */

	@DeleteMapping("/deleteTrainer/{trainerId}/{userId}")
	public ResponseEntity<List<Trainer>> deleteTrainer(@PathVariable("trainerId") int trainerId,
			@PathVariable("userId") int userId) {
		List<Trainer> trainer = trainerService.deleteTrainer(trainerId, userId);
		if (trainer == null) {
			logger.warn("Trainer Details not found by trainer ID " + trainerId + " to delete");
			throw new DataNotFoundException("No Trainer present with the given id: " + trainerId);
		}
		logger.warn("Trainer Details Deleted Successfully with trainer ID " + trainerId);
		return new ResponseEntity<List<Trainer>>(trainer, HttpStatus.OK);
	}

}
