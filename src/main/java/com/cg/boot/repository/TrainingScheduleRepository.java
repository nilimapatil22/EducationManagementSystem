package com.cg.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.TrainingSchedule;
/**
 * @author Priyanka. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class TrainingSchedule that is being
 *         managed.
 *
 */
@Repository
public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Integer> {

	List<TrainingSchedule> findAllByStudentId(int studentId);

}
