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

import com.cg.boot.model.Message;
import com.cg.boot.repository.MessageRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
/**
 * 
 * @author Madhuri
 *
 */
@SpringBootTest
class MessageServiceTest {
    
	@Autowired
	IMessageService iMessageService;

	@MockBean
	MessageRepository repository;

	MessageService listMock = mock(MessageService.class, "myMock");
    /**
     * This method test getAllMessages() method and also specify the condition if
	 * the size of message list is exactly equal to given size in the function then
	 * test will pass.
	 * 
	 * @param list : {@link List}
     */
	
	@Test
	public void getAllMessagesTest() {
		when(repository.findAll())
				.thenReturn(Stream.of(new Message(103, "get ready", 1, 34, "2021-01-12")).collect(Collectors.toList()));
		assertNotNull(iMessageService.getAllMessages());
	}
    

	/**
	 * This method test getMessageByStudentId() method and also specify the condition.
	 * Check the message by student Id.
	 * 
	 * @param id : {@link Integer}
	 */
	@Test
	public void getMessagesByStudentIdTest() {
		when(repository.findAllByStudentId(109))
				.thenReturn(Stream.of(new Message(103, "get ready", 1, 34, "2021-01-12")).collect(Collectors.toList()));
		assertEquals(1, iMessageService.getMessagesByStudentId(109).size());
	}
    
	/**
	 * This method test addMessage() method and also specify the condition. Check
	 * that the data is added or not.
	 * 
	 * @param value : {@link Value}
	 */
	@Test
	public void addMessagesTest() {
		Message message = new Message("Exams have postponed", 0, 108, "2021-01-23");
		when(repository.save(message)).thenReturn(message);
		assertEquals(message, iMessageService.addMessage(message));

	}
    
	/**
	 * This method test isValidMessage() method using Mockito and specify the condition.
	 * Validate the message. Return true or false based on condition.
	 * 
	 * @param message : {@link String}
	 */
	@Test
	public void isValidMessageTest() {
		when(listMock.isValidMessage("Good luck")).thenReturn(true);
		boolean flag = listMock.isValidMessage("Hi");
		verify(listMock).isValidMessage("Hii");
		assertFalse(flag);;

	}
    
	/**
	 * This method test isValidDate() method and specify the condition.
	 * Validate the date.Return true or false based on condition.
	 * 
	 * @param date : {@link String}
	 */
	@Test
	public void isValidDateTest() {
		when(listMock.isValidDate("2021-01-02")).thenReturn(true);
		boolean flag = listMock.isValidDate("2021-01-12");
		verify(listMock).isValidDate("2021-01-12");
		assertThat(flag);
	}

}
