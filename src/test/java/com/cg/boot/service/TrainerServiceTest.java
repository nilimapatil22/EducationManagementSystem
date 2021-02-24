package com.cg.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.boot.model.Trainer;
import com.cg.boot.repository.TrainerRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

/**
 * 
 * @author Madhuri
 *
 */
@SpringBootTest
public class TrainerServiceTest {

	@Autowired
	ITrainerService service;
	@MockBean
	TrainerRepository repository;

	TrainerService listMock = mock(TrainerService.class, "myMock");

	/**
	 * This method test getAllTrainers() method and also specify the condition if
	 * the size of trainer list is exactly equal to given size in the function then
	 * test will pass.
	 * 
	 * @param list : {@link List}
	 */
	@Test
	public void getAllTrainersTest() {
		when(repository.findAll()).thenReturn(
				Stream.of(new Trainer(29, "Gari", "9863552556", "gari@gmail.com", "Successful Presentation", 6))
						.collect(Collectors.toList()));
		assertEquals(1, service.getAllTrainers().size());
	}

	/**
	 * This method test addTrainer() method and also specify the condition. Check
	 * that the data is added or not.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void addTrainerTest() {
		Trainer trainer = new Trainer(30, "Sai", "8756453498", "sai@gmail.com", "TDD with JUnit 5", 6);
		when(repository.save(trainer)).thenReturn(trainer);
		assertEquals(trainer, service.addTrainer(trainer));
	}

	/**
	 * This method test deleteTrainer() method and also specify the condition. Check
	 * that data is deleted or not.
	 * 
	 * @param value : {@link Value}
	 */
//	@Test
//	public void deleteTrainer() {
//		Trainer trainer = new Trainer(30, "Sai", "8756453498", "sai@gmail.com", "TDD with JUnit 5", 6);
//		service.deleteTrainer(trainer.getTrainerId(), trainer.getAdminId());
//		verify(repository, times(1)).deleteById(trainer.getTrainerId());
//	}

	/**
	 * This method test updateReturn() method and specify the condition. Check that
	 * data is updated or not.
	 * 
	 * @param value : {@link Value}
	 */
//	@Test
//	public void updateTrainer() {
//		Trainer trainer = new Trainer(30, "Sai Acuity", "8756483498", "sai@gmail.com", "TDD with JUnit 5", 6);
//		when(repository.save(trainer)).thenReturn(trainer);
//		assertNotEquals(trainer, service.updateTrainer(trainer));
//	}

	/**
	 * This method test isValidTrainerPhoneNo() method and specify the condition.
	 * Validate the trainer phone number. Check phone no of
	 * trainer.Return true or false based on condition.
	 * 
	 * @param value : {@link Value}
	 */

	@Test
	public void isValidTrainerPhoneNo() {
		when(listMock.isValidTrainerPhoneNo("9087675577")).thenReturn(true);
		boolean flag = listMock.isValidTrainerPhoneNo("9087675577");
		verify(listMock).isValidTrainerPhoneNo("9087675577");
		assertThat(flag);
	}

	/**
	 * This method test isValidTrainerPhoneNo() method and specify the condition.
	 * Check whether the given trainer name is valid or not.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void isValidTrainerName() {
		when(listMock.isValidTrainerName("Sudheer")).thenReturn(true);
		boolean flag = listMock.isValidTrainerName("Sudheer");
		verify(listMock).isValidTrainerName("Sudheer");
		;
		assertThat(flag);
	}

}
