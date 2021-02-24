package com.cg.boot.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.repository.PreviousProgressDetailsRepository;
import com.cg.boot.repository.ProgressDetailsRepository;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

/**
 * @author Nilima
 *
 */
@Service
@Transactional
public class ProgressDetailsService implements IProgressDetailsService {

	@Autowired
	ProgressDetailsRepository repository;
	@Autowired
	PreviousProgressDetailsRepository previousRepository;

	PreviousProgressDetails previousDetails;
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;

	/**
	 * This method finds all progress details by grade and return list of progress
	 * details
	 * 
	 * @param grade{@link String}
	 * @return {@link List}
	 */

	@Override
	public List<ProgressDetails> getAllProgressDetails(String grade) {
		return repository.findAllByGrade(grade);
	}

	/**
	 * This method check that the id is student id or not if it is student id then
	 * it finds all previous progress details by student id and return list of
	 * previous progress details
	 * 
	 * @param studentId {@link Integer}
	 * @return {@link List}
	 */

	@Override
	public List<PreviousProgressDetails> getAllProgressDetailsByStudentId(int studentId) {
		userService.validateStudentId(studentId);
		return previousRepository.findAllByStudentId(studentId);
	}

	/**
	 * This method saves progress in progress details and previous progress details
	 * database Check input validation and return saved progress details.
	 * 
	 * @param progressDetails {@link ProgressDetails }
	 * @return progressDetails {@link ProgressDetails}
	 */
	@Override
	public ProgressDetails addProgressDetails(@Valid ProgressDetails progressDetails) {
		userService.validateAdminId(progressDetails.getAdminId());
		userService.validateStudentId(progressDetails.getStudentId());
		//courseService.validateCourse(progressDetails.getCourseId());
		previousDetails = new PreviousProgressDetails(progressDetails.getGrade(), progressDetails.getDate(),
				progressDetails.getCourseId(),progressDetails.getAdminId(), progressDetails.getStudentId());
		previousRepository.save(previousDetails);
		return repository.save(progressDetails);
	}

	/**
	 * This method update progress details. Check input validation and return
	 * updated progress details.
	 * 
	 * @throws DataNotFoundException
	 * @param progressDetails {@link ProgressDetails}
	 * @return ProgressDetails {@link ProgressDetails}
	 */
	@Override
	public ProgressDetails updateProgressDetails(ProgressDetails progressDetails,int gradeId) {
		return repository.findById(gradeId).map(progressDetailsEntity ->{
			progressDetailsEntity.setGrade(progressDetails.getGrade());
			progressDetailsEntity.setDate(progressDetails.getDate());
			progressDetailsEntity.setCourseId(progressDetails.getCourseId());
			progressDetailsEntity.setStudentId(progressDetails.getStudentId());
			progressDetailsEntity.setAdminId(progressDetails.getAdminId());
		return repository.save(progressDetails);
		}).orElseThrow(()->new DataNotFoundException());
	
//		userService.validateAdminId(progressDetails.getAdminId());
//		userService.validateStudentId(progressDetails.getStudentId());
//		if (!isValidDate(progressDetails.getDate())) {
//			throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
//		}
//		previousDetails = new PreviousProgressDetails(progressDetails.getGrade(), progressDetails.getDate(),
//				progressDetails.getCourseId(),progressDetails.getAdminId(), progressDetails.getStudentId());
//		previousRepository.save(previousDetails);
//		return repository.save(progressDetails);
	}

	/**
	 * This method first check it is admin id or not. If it is admin id then it
	 * delete progress details based on progress id and return list of progress
	 * details except deleted one.
	 * 
	 * @param progressId {@link Integer}
	 * @param userId     {@link Integer}
	 * @return list {@link List}
	 */
	@Override
	public List<ProgressDetails> deleteProgressDetails(int progressId) {
		//userService.validateAdminId(userId);
		repository.deleteById(progressId);
		return repository.findAll();
	}

	/**
	 * This method accepts grade id and find the progress detail based on grade id.
	 * Return progress details based on grade id otherwise return null.
	 * 
	 * @param gradeId {@link Integer}
	 * @return progress details {@link ProgressDetails}
	 */

	@Override
	public ProgressDetails getProgressDetails(int gradeId) {
		return repository.findById(gradeId).orElse(null);
	}

	/**
	 * This method returns list of all progress details
	 * 
	 * @return list {@link List}
	 */
	@Override
	public List<ProgressDetails> getAllProgressDetails() {
		return repository.findAll();
	}

	/**
	 * This method validate date, It matches with the format given in the method.If
	 * it matched then it will return flag as true otherwise flag as false.
	 * 
	 * @param date {@link String}
	 * @return flag {@link Boolean}
	 */
	@Override
	public boolean isValidDate(String date) {
		boolean flag = false;
		String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(date);
		if (m.matches()) {
			flag = true;
		}
		return flag;

	}

	@Override
	public List<PreviousProgressDetails> getAllPreviousProgressDetails() {
		return previousRepository.findAll();
	}
}
