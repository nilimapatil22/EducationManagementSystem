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
import com.cg.boot.model.Payment;
import com.cg.boot.service.IPaymentService;

/**
 * @author Nilima
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentPaymentController {

	@Autowired
	IPaymentService service;
	Logger logger = LoggerFactory.getLogger(StudentPaymentController.class);

	/**
	 * This method accepts payment Id which user has inserted. Return response
	 * entity containing payment details based on payment Id.
	 * 
	 * @throws DataNotFoundException
	 * @param paymentId : {@link Integer}
	 * @return {@link ResponseEntity}: payment {@link Payment}, {@link HttpStatus}
	 */
	@GetMapping("/getStudentPayment/{paymentId}")
	public ResponseEntity<Payment> getPayment(@PathVariable("paymentId") int paymentId) throws DataNotFoundException {
		Payment payment = service.getPayment(paymentId);
		if (payment == null) {
			logger.warn("Payment Details not found with ID " + paymentId);
			throw new DataNotFoundException("Payment Transaction Not Found By these ID");
		}
		logger.info("Payment Details Return successfully");
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}

	/**
	 * This method returns list of all payments
	 * 
	 * @throws DataNotFoundException
	 * @return {@link ResponseEntity}: payment {@link Payment}, {@link HttpStatus}
	 */

	@GetMapping("/getAllStudentPayment")
	public ResponseEntity<List<Payment>> getPayment() throws DataNotFoundException {
		List<Payment> payment = service.getAllPayment();
		if (payment.isEmpty()) {
			logger.warn("Student Payment Details Not found ");
			throw new DataNotFoundException("Payment Transaction Not Found");
		}
		logger.info("All Payment Details return successfully");
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}

	/**
	 * This method accepts and saves Payment which user has inserted through object.
	 * Return payment Id which has been saved.
	 * 
	 * @param payment : {@link Payment}
	 * @return {@link ResponseEntity}: paymentInfo {@link Payment},
	 *         {@link HttpStatus}
	 */
	@PostMapping("/addPaymentByStudent")
	public ResponseEntity<Integer> addPayment(@Valid @RequestBody Payment payment) throws DataNotFoundException {
		Payment paymentInfo = service.addPayment(payment);
		logger.info("Payment Transaction successful");
		return new ResponseEntity<Integer>(paymentInfo.getPaymentId(), HttpStatus.OK);
	}

}
