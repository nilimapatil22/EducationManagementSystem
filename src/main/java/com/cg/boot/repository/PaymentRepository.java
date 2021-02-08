package com.cg.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.Payment;

/**
 * @author Nilima. This interface extends JpaRepository which provides JPA
 *         functionalities for the entity class Payment that is being
 *         managed.
 *
 */
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
