package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.Trainer;
/**
 * @author Madhuri. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class Trainer that is being
 *         managed.
 *
 */
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	
	//public List<Trainer> findAllByStudentId(int id);
    
}
