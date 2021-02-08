package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.TrainingSchedule;
/**
 * @author Priyanka. This interface contains abstract methods TrainingScheduleService
 *         class.
 *
 */
public interface ITrainingScheduleService {

	TrainingSchedule addSchedule(TrainingSchedule schedule);

	TrainingSchedule getSchedule(int scheduleId);

	List<TrainingSchedule> getAllSchedules();

	List<TrainingSchedule> getScheduleByStudentId(int studentId);

	TrainingSchedule updateSchedule(TrainingSchedule schedule);

	List<TrainingSchedule> deleteSchedule(int scheduleId,int userId);

	boolean isValidDate(String date);

}
