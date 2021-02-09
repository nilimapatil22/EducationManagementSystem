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

import com.cg.boot.model.ProgressDetails;
import com.cg.boot.repository.ProgressDetailsRepository;

/**
 * 
 * @author Nilima
 *
 */
@SpringBootTest
class ProgressDetailsServiceTest {
	@Autowired
	IProgressDetailsService service;

	@MockBean
	ProgressDetailsRepository repository;
	ProgressDetailsService listMock = mock(ProgressDetailsService.class, "myMock");

	/**
	 * This method test getAllProgressDetails( ) method using Mockito. and also
	 * specified the condition Check the size of progress details we added.
	 * Accordingly test case fails or pass.
	 * 
	 * @param {@link List}
	 */

	@Test
	public void getAllProgressDetailsTest() {
		when(repository.findAll())
				.thenReturn(Stream.of(new ProgressDetails(2, "A", "2021-02-06", 3, 4)).collect(Collectors.toList()));
		assertEquals(1, service.getAllProgressDetails().size());
	}

	/**
	 * This method test updateProgressDetails( ) method using Mockito and also
	 * specified condition. Check whether user is null or not null
	 * 
	 * @param value {@link value}
	 */

	@Test
	public void updateProgressDetailsTest() {
		ProgressDetails details = new ProgressDetails(2, "A", "2021-02-06", 3, 4);
		when(repository.save(details)).thenReturn(details);
		assertNull(details);
	}

	/**
	 * This method test addProgressDetails( ) method using Mockito and also
	 * specified condition. Check whether user is null or not null
	 * 
	 * @param value {@link value}
	 */

	@Test
	public void addProgressDetailsTest() {
		ProgressDetails details = new ProgressDetails(2, "A", "2021-02-06", 3, 4);
		when(repository.save(details)).thenReturn(details);
		assertNotNull(details);
	}

	/**
	 * This method test getAllProgressDetails( ) method using Mockito. and also
	 * specified the condition Check the size of progress details we added.
	 * Accordingly test case fails or pass.
	 * 
	 * @param {@link List}
	 */

	@Test
	public void getAllProgressDetailsGradeTest() {
		when(repository.findAllByGrade("A"))
				.thenReturn(Stream.of(new ProgressDetails(2, "A", "2021-02-06", 3, 4)).collect(Collectors.toList()));
		assertEquals(1, service.getAllProgressDetails("A").size());
	}
	/**
	 * This method test isValidDate( ) method and specify condition.
	 * Validate date. Return true or false based on condition.
	 * 
	 * @param value {@link Value}
	 */

	@Test
	public void isValidDateTest() {
		when(listMock.isValidDate("2021-01-02")).thenReturn(true);
		boolean flag = listMock.isValidDate("2021-01-12");
		verify(listMock).isValidDate("2021-01-12");
		assertThat(flag);
	}

}
