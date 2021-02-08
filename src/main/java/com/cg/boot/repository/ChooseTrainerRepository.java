package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.ChooseTrainer;
/**
 * @author Nilima. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class ChooseTrainer that is being
 *         managed.
 *
 */
@Repository
public interface ChooseTrainerRepository extends JpaRepository<ChooseTrainer, Integer>{

}
