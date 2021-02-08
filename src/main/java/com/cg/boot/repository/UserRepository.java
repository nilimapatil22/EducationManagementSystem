package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.User;
/**
 * @author Prajakta. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class User that is being
 *         managed.
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

}
