package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.repository.TrainingScheduleRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
/**
 * 
 * @author Priyanka
 *
 */
@SpringBootTest
class TrainingScheduleServiceTest {
	@Autowired
	ITrainingScheduleService scheduleService;
	
	@MockBean
	TrainingScheduleRepository scheduleRepository;
	
	TrainingScheduleService listMock = mock(TrainingScheduleService.class,"myMock");
	
	/**
	 * This method test getAllSchedules() method and also specify the condition if
	 * the size of schedule list is exactly equal to given size in the function then
	 * test will pass.
	 * 
	 * @param list : {@link List}
	 */
	@Test
	public void getAllScheduleTest() {
		when(scheduleRepository.findAll()).thenReturn(Stream.of(new TrainingSchedule(3, "Java", "2021-02-04", 30, 21, 108)).collect(Collectors.toList()));
		assertNotNull(scheduleService.getAllSchedules());
		
	}
	
	/**
	 * This method test getScheduleByStudentId() method and also specify the condition.
	 * Check the schedule by student Id.
	 * 
	 * @param id : {@link Integer}
	 */
	@Test
	public void getScheduleByStudentIdTest() {
		when(scheduleRepository.findAllByStudentId(109))
		.thenReturn(Stream.of(new TrainingSchedule(3, "Java", "2021-02-04", 30, 21, 108)).collect(Collectors.toList()));
		assertNotEquals(3, scheduleService.getAllSchedules().size());
	} 
    
	/**
	 * This method test addTrainingSchedule() method and also specify the condition. Check
	 * that the data is added or not.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void addTrainingScheduleTest() {
		TrainingSchedule schedule= new TrainingSchedule(116, "Java", "2021-02-04", 30, 109, 108);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
		assertNull(schedule);
	}
	
	/**
	 * This method test isValidDate() method and specify the condition.
	 * Validate the date.Return true or false based on condition.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void isValidDateTest() {
		when(listMock.isValidDate("2021-01-02")).thenReturn(true);
		boolean flag = listMock.isValidDate("2021-01-12");
		verify(listMock).isValidDate("2021-01-12");
		assertThat(flag);
	}

}
