package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.Message;
/**
 * @author Priyanka. This interface contains abstract methods MessageService
 *         class.
 *
 */
public interface IMessageService {

	public Message addMessageByStudent(Message message);
	public Message addMessageByAdmin(Message message);

	public Message getMessage(int messageId);

	public List<Message> getAllMessages();

	public List<Message> getMessagesByStudentId(int studentId);

	public Message updateMessage(Message message);

	public List<Message> deleteMessage(int messageId);

	public boolean isValidMessage(String message);

	public boolean isValidDate(String date);

}
