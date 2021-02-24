package com.cg.boot.admin.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class TrainerController {
	@Autowired
	ITrainerService service;
	
    /**
     * This method accepts and saves trainer which user has inserted through object.
	 * Return an object of trainer containing all arguments which has been saved.
	 * 
     * @param trainer : {@link Trainer}
     * @return trainer : {@link Trainer}
     */
	
	// http://localhost:7171/api/addTrainer
	@PostMapping("/addTrainer")
	public Trainer addTrainer(@Valid @RequestBody Trainer trainer) {
		Trainer addTrainer = service.addTrainer(trainer);
		return addTrainer;
	}
	
	/**
	 *  This method accepts trainer Id which user has inserted. Return response entity
	 * containing trainer details based on trainer Id.
	 * @param id : {@link Integer}
	 * @return {@link ResponseEntity} : trainer {@link Trainer}, {@link HttpStatus}
	 */
    
	// http://localhost:7171/api/getTrainer/10
	@GetMapping("/getTrainer/{id}")
	public ResponseEntity<Trainer> getTrainer(@PathVariable("id") int id) {

		Trainer getTrainer = service.getTrainer(id);
		if (getTrainer == null) {
			throw new DataNotFoundException("No Trainer present with the given id: " + id);
		}
		return new ResponseEntity<Trainer>(getTrainer, HttpStatus.OK);
	}
	
	/**
	 * This method returns list of all trainers.
	 * @return  {@link ResponseEntity}: trainerList {@link List}, {@link HttpStatus}
	 *
	 */
    
	// http://localhost:7171/api/getAllTrainers
	@GetMapping("/getAllTrainers")
	public ResponseEntity<List<Trainer>> getTrainers() throws Exception {
		List<Trainer> getTrainers = service.getAllTrainers();
		return new ResponseEntity<List<Trainer>>(getTrainers, HttpStatus.OK);
	}
	
	/**
	 * This method accepts and update courses which user has inserted through
	 * object. Return response entity containing details of course which has been
	 * updated.
	 * 
	 * @param trainer : {@link Trainer}
	 * @return  {@link ResponseEntity}: trainer {@link Trainer}, {@link HttpStatus}
	 */
    
	// http://localhost:7171/api/updateTrainer/
	@PutMapping("/updateTrainer/{trainerId}")
	public ResponseEntity<Trainer> updateTrainer(@PathVariable("trainerId") int trainerId, @RequestBody Trainer trainer) {
		Trainer updateTrainer = service.updateTrainer(trainerId,trainer);
		return new ResponseEntity<Trainer>(updateTrainer, HttpStatus.OK);
	}
	
	/**
	 * This method accepts trainer Id to delete trainer details based on trainer Id. Accepts
	 * user Id to check authorized user to perform operation. Return list of
	 * remaining trainers except deleted one.
	 * 
	 * @param trainerId  : {@link Integer}
	 * @param userId : {@link Integer}
	 * @return  {@link ResponseEntity}: trainer {@link List}, {@link HttpStatus}
	 */
    
	// http://localhost:7171/api/deleteTrainer/160
	@DeleteMapping("/deleteTrainer/{trainerId}")
	public ResponseEntity<List<Trainer>> deleteTrainer(@PathVariable("trainerId") int trainerId) {
		List<Trainer> trainer = service.deleteTrainer(trainerId);
		if (trainer == null) {
			throw new DataNotFoundException("No Trainer present with the given id: " + trainerId);
		}
		return new ResponseEntity<List<Trainer>>(trainer, HttpStatus.OK);
	}

}
