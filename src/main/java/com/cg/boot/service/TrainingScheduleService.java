package com.cg.boot.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.repository.TrainingScheduleRepository;

@Service
@Transactional
public class TrainingScheduleService implements ITrainingScheduleService{
    @Autowired 
	TrainingScheduleRepository repository;
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    Logger logger=LoggerFactory.getLogger(TrainingScheduleService.class);
    
	/*
	 * Add training schedule
	 */
    @Override
	public TrainingSchedule addSchedule(TrainingSchedule schedule) {
    	userService.validateAdminId(schedule.getCreatedByUserId());
    	userService.validateStudentId(schedule.getStudentId());
    	courseService.validateCourse(schedule.getCourseName());
        TrainingSchedule trainingSchedule = repository.save(schedule);
		return trainingSchedule;
	}
    
    /*
	 * Get schedule based on schedule id
	 */

	@Override
	public TrainingSchedule getSchedule(int scheduleId) {
		return repository.findById(scheduleId).orElse(null);
	}
	
	/*
	 * Get all schedules
	 */

	@Override
	public List<TrainingSchedule> getAllSchedules() {
		return repository.findAll();
	}
	/*
	 * Get schedules based on student id
	 */

	@Override
	public List<TrainingSchedule> getScheduleByStudentId(int studentId) {
		List<TrainingSchedule> scheduleList = repository.findAllByStudentId(studentId);
		if(scheduleList.isEmpty()) {
			logger.warn("No schedules are present with given student id: "+studentId);
			throw new DataNotFoundException("No schedules are present with given student id: "+studentId);
		}
		return scheduleList;
	}
	/*
	 * Update Schedule
	 */

	@Override
	public TrainingSchedule updateSchedule(TrainingSchedule schedule) { 
		userService.validateAdminId(schedule.getCreatedByUserId());
		userService.validateStudentId(schedule.getStudentId());
    	courseService.validateCourse(schedule.getCourseName());
    	if(!isValidDate(schedule.getDate())) {
    		logger.error("Invalid date format");
    		throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
    	}
		if(getSchedule(schedule.getScheduleId())==null) {
			logger.error("No Schedule found to update");
			throw new DataNotFoundException("No schedule present to update");
		}
		return repository.save(schedule);
	}
	/*
	 * Delete Schedule
	 */
    @Override
	public List<TrainingSchedule> deleteSchedule(int scheduleId,int userId) {
    	userService.validateAdminId(userId);
		if( getSchedule(scheduleId)==null) {
			logger.warn("No schedule present to delete");
			throw new DataNotFoundException("No schedule present to delete with given id: "+scheduleId);
		}
		repository.deleteById(scheduleId);
		return repository.findAll();
	}
    
	
	@Override
    public boolean isValidDate(String date) {
		boolean flag = false;
		 String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"; 
		 Pattern p = Pattern.compile(regex); Matcher m = p.matcher(date);
		 if(m.matches()) {
			 flag = true;
		 }
		 return flag;
		
	}

	
	

}
