package com.cg.boot.service;

import java.util.List;

import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.ChooseTrainer;
import com.cg.boot.model.Trainer;
import com.cg.boot.repository.ChooseTrainerRepository;
import com.cg.boot.repository.TrainerRepository;

/**
 * 
 * @author Madhuri
 *
 */
@Service
@Transactional
public class TrainerService implements ITrainerService {
	@Autowired
	TrainerRepository repository;
	@Autowired
	ChooseTrainerRepository chooseTrainerRepository;
	@Autowired
	UserService userService;

	ChooseTrainer trainee;
	/**
	 * This method accepts and saves trainer details which user has inserted through
	 * object. Return an object of trainer containing all arguments which has been
	 * saved. Validate the user is admin.
	 * 
	 * @param trainer : {@link Trainer}
	 * @return Trainer : {@link Trainer}
	 */
	@Override
	public Trainer addTrainer(Trainer trainer) {
//		userService.validateAdminId(trainer.getAdminId());
//		if (trainer != null) {
//			if (!isValidTrainerName(trainer.getTrainerName())) {
//				throw new DataNotFoundException();
//			} else if (!isValidTrainerPhoneNo(trainer.getPhoneNo())) {
//				throw new DataNotFoundException();
//			}
//		}
		return repository.save(trainer);
	}

	/**
	 * This method accepts and update trainers detail which user has inserted
	 * through object. Return response entity containing details of trainer which
	 * has been updated.
	 * 
	 * @param trainer : {@link Trainer}
	 * @return {@link ResponseEntity}: trainer {@link Trainer}, {@link HttpStatus}
	 */

	@Override
	public Trainer updateTrainer(int trainerId,Trainer trainer) {
		
		return repository.findById(trainerId).map(trainerEntity -> {
			trainerEntity.setTrainerName(trainer.getTrainerName());
			trainerEntity.setPhoneNo(trainer.getPhoneNo());
			trainerEntity.setEmail(trainer.getEmail());
			trainerEntity.setCourseName(trainer.getCourseName());
			return repository.save(trainerEntity);
		}).orElseThrow(() -> new DataNotFoundException());
//		userService.validateAdminId(trainer.getAdminId());
//		Trainer updateTrainer = getTrainer(trainer.getTrainerId());
//		if (updateTrainer != null) {
//			if (!isValidTrainerName(trainer.getTrainerName())) {
//				throw new DataNotFoundException();
//			} else if (!isValidTrainerPhoneNo(trainer.getPhoneNo())) {
//				throw new DataNotFoundException();
//			}
//			updateTrainer = repository.save(trainer);
//		}
//		return updateTrainer;
	}

	/**
	 * This method validate the trainer phone number. Check phone no of trainer.
	 * Return true or false based on condition.
	 * 
	 * @param phoneNo : {@link String}
	 * @return flag : {@link Boolean}
	 */

	@Override
	public boolean isValidTrainerPhoneNo(String phoneNo) {
		boolean flag = false;
		String number = String.valueOf(phoneNo);
		String phoneregex = "^\\d{10}$";

		if (Pattern.matches(phoneregex, number)) {
			flag = true;
		} else {
			throw new DataNotFoundException("Invalid Phonenumber ");
		}
		return flag;
	}

	/**
	 * This method validate the trainer Name. Check Name of trainer. Return true or
	 * false based on condition.
	 * 
	 * @param trainerName : {@link String}
	 * @return flag : {@link Boolean}
	 */
	@Override
	public boolean isValidTrainerName(String trainerName) {
		boolean flag = false;
		String nameregex = "[A-Z][a-z]*";
		if (Pattern.matches(nameregex, trainerName)) {
			flag = true;
		} else {
			throw new DataNotFoundException("Given Trainer Name is Invalid ");
		}
		return flag;
	}

	/**
	 * This method accepts trainer Id which user has inserted. Return response
	 * entity containing course details based on trainer Id.
	 * 
	 * @param id : {@link Integer}
	 * @return {@link ResponseEntity}: trainer {@link List}, {@link HttpStatus}
	 * 
	 */
	@Override
	public Trainer getTrainer(int id) {
		return repository.findById(id).orElse(null);
	}

	/**
	 * This method performs choose Course Details with trainer details. Check if
	 * selected trainer id is present in database or not. Return course name and Id
	 * based on student Id.
	 * 
	 * @param trainerId : {@link Integer}
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity}: trainer {@link List}, {@link HttpStatus}
	 * 
	 */
	@Override
	public ChooseTrainer getTrainerDetails(int trainerId, int studentId) {
		userService.validateStudentId(studentId);
		Trainer trainer = repository.findById(trainerId).orElse(null);
		ChooseTrainer chooseTrainer = null;
		if (trainer != null) {
			chooseTrainer = new ChooseTrainer(studentId, trainerId, trainer.getTrainerName(), trainer.getCourseName());

		}
		chooseTrainerRepository.save(chooseTrainer);
		return chooseTrainer;
	}

	/**
	 * These method return all trainers detail.
	 * 
	 * return {@link ResponseEntity}: trainer {@link List}, {@link HttpStatus}
	 */
	@Override
	public List<Trainer> getAllTrainers() {
		return repository.findAll();
	}

	/**
	 * This method finds trainer by passed user Id. Returns list of trainers based
	 * on trainer Id. Check whether list of courses is empty or not.
	 * 
	 * @param trainerId : {@link Integer}
	 * @param userId    : {@link Integer}
	 * @return {@link List}
	 */
	@Override
	public List<Trainer> deleteTrainer(int trainerId) {
		repository.deleteById(trainerId);
		return repository.findAll();
	}

	@Override
	public ChooseTrainer chooseTrainerDetails(int studentId, @Valid ChooseTrainer choosetrainer) {
	choosetrainer.setStudentId(studentId);
		return chooseTrainerRepository.save(choosetrainer);
	}

}
