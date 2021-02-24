package com.cg.boot.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
/**
 * @author Nilima. This interface contains abstract methods ProgressDetailsService
 *         class.
 *
 */
public interface IProgressDetailsService {

	List<ProgressDetails> getAllProgressDetails(String grade);

	ProgressDetails addProgressDetails(@Valid ProgressDetails progressDetails);

	//ProgressDetails updateProgressDetails(ProgressDetails progressDetails);

	//List<ProgressDetails> deleteProgressDetails(int progressId,int userId);

	ProgressDetails getProgressDetails(int gradeId);

	List<ProgressDetails> getAllProgressDetails();
	List<PreviousProgressDetails> getAllPreviousProgressDetails();

	List<PreviousProgressDetails> getAllProgressDetailsByStudentId(int studentId);

	boolean isValidDate(String date);

	/**
	 * This method first check it is admin id or not. If it is admin id then it
	 * delete progress details based on progress id and return list of progress
	 * details except deleted one.
	 * 
	 * @param progressId {@link Integer}
	 * @param userId     {@link Integer}
	 * @return list {@link List}
	 */
	List<ProgressDetails> deleteProgressDetails(int progressId);

	/**
	 * This method update progress details. Check input validation and return
	 * updated progress details.
	 * 
	 * @throws DataNotFoundException
	 * @param progressDetails {@link ProgressDetails}
	 * @return ProgressDetails {@link ProgressDetails}
	 */
	ProgressDetails updateProgressDetails(ProgressDetails progressDetails, int gradeId);

	

}
