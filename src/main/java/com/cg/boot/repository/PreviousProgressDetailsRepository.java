package com.cg.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.PreviousProgressDetails;
/**
 * @author Nilima. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class PreviousProgressDetails that is being
 *         managed.
 *
 */
@Repository
public interface PreviousProgressDetailsRepository extends JpaRepository<PreviousProgressDetails, Integer> {
	public List<PreviousProgressDetails> findAllByStudentId(int id);
}
