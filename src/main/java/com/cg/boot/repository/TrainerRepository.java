package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.Trainer;
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	
	//public List<Trainer> findAllByStudentId(int id);
    
}
