package com.cg.boot.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.service.IMessageService;

/**
 * 
 * @author Priyanka
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentMessageController {
	@Autowired
	IMessageService messageService;
	Logger logger = LoggerFactory.getLogger(StudentMessageController.class);

	/**
	 * This method accepts and saves message which student has inserted through object.
	 * Return an object of message containing all arguments which has been saved.
	 * 
	 * @param messageDetails : {@link Message}
	 * @return messageInfo : {@link Message}
	 */
	@PostMapping("/addStudentMessage")
	public Message addMessage(@Valid @RequestBody Message messageDetails) {
		Message messageInfo = messageService.addMessageByStudent(messageDetails);
		logger.info("Message added successfully");
		return messageInfo;

	}

	/**
	 * This method accepts message Id which user has inserted. Return response
	 * entity containing message details based on message Id.
	 * 
	 * @throws DataNotFoundException
	 * @param messageId : {@link Integer}
	 * @return {@link ResponseEntity} : message {@link Message}, {@link HttpStatus}
	 */
	@GetMapping("/getStudentMessage/{messageId}")
	public ResponseEntity<Message> getMessage(@PathVariable("messageId") int messageId) {
		Message message = messageService.getMessage(messageId);
		if (message == null) {
			logger.warn("No message present with given id: " + messageId);
			throw new DataNotFoundException("No message present with given id: " + messageId);
		}
		logger.info("Message return successfully");
		return new ResponseEntity<Message>(message, HttpStatus.OK);

	}

	/**
	 * This method returns list of all messages.
	 * 
	 * @return {@link ResponseEntity}: messageList {@link List}, {@link HttpStatus}
	 */
	@GetMapping("/getAllStudentMessages")
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> messageList = messageService.getAllMessages();
		logger.info("All messages return successfully");
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);

	}

	/**
	 * This method accepts student Id which user has inserted. Return response
	 * entity containing list of message details based on student Id.
	 * 
	 * @param studentId : {@link Integer}
	 * @return {@link ResponseEntity}: messageList {@link List}, {@link HttpStatus}
	 */
	@GetMapping("/getStudentMessagesByStudentId/{studentId}")
	public ResponseEntity<List<Message>> getMessagesByStudentId(@PathVariable("studentId") int studentId) {
		List<Message> message = messageService.getMessagesByStudentId(studentId);
		logger.info("Message return successfully by student Id: " + studentId);
		return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
	}

}
