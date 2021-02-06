package com.cg.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	
	public List<Trainer> findAllByStudentId(int id);
    
}
