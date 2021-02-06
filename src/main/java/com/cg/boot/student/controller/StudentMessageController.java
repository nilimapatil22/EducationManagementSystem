package com.cg.boot.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Message;
import com.cg.boot.service.IMessageService;

@RestController
@RequestMapping("/api")
public class StudentMessageController {
	@Autowired
	IMessageService messageService;
	Logger logger=LoggerFactory.getLogger(StudentMessageController.class);
	/*
	 * Add Messages
	 */
	@PostMapping("/addStudentMessage")
	public Message addMessage(@Valid @RequestBody Message messageDetails) {
		Message messageInfo = messageService.addMessage(messageDetails);
		logger.info("Message Added Successfully");
		return messageInfo;

	}

	/*
	 * Get messages based on message id
	 */
	@GetMapping("/getStudentMessage/{messageId}")
	public ResponseEntity<Message> getMessage(@PathVariable("messageId") int messageId) {
		Message message = messageService.getMessage(messageId);
		if (message == null) {
			logger.warn("No message present with given id: " + messageId);
			throw new DataNotFoundException("No message present with given id: " + messageId);
		}
		logger.info("message return Successfully");
		return new ResponseEntity<Message>(message, HttpStatus.OK);

	}

	/*
	 * Get all messages
	 */
	@GetMapping("/getAllStudentMessages")
	public ResponseEntity<List<Message>> getAllMessages() {
		List<Message> messageList = messageService.getAllMessages();
		logger.info("All messages return Successfully");
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);

	}

	/*
	 * Get messages based on student id
	 */
	@GetMapping("/getStudentMessagesByStudentId/{studentId}")
	public ResponseEntity<List<Message>> getMessagesByStudentId(@PathVariable("studentId") int studentId) {
		List<Message> message = messageService.getMessagesByStudentId(studentId);
		logger.info("message return Successfully By Student ID "+studentId);
		return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
	}

}
