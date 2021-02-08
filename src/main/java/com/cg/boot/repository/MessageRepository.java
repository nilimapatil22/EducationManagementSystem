package com.cg.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.boot.model.Message;
/**
 * @author Priyanka. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class Message that is being
 *         managed.
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	public List<Message> findAllByStudentId(int id);

}
