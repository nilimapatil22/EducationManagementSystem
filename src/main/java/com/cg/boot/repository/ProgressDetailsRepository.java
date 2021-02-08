package com.cg.boot.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.ProgressDetails;
/**
 * @author Nilima. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class ProgressDetails that is being
 *         managed.
 *
 */
public interface ProgressDetailsRepository extends JpaRepository<ProgressDetails,Integer>{
	public List<ProgressDetails> findAllByGrade(String grade);

}
