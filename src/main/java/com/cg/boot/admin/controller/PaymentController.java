package com.cg.boot.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Payment;
import com.cg.boot.service.IPaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	IPaymentService service;
	Logger logger=LoggerFactory.getLogger(PaymentController.class);
	/*
	 * get Payment Details by id
	 */
	@GetMapping("/getmyPayment/{paymentId}")
	public ResponseEntity<Payment> getPayment(@PathVariable("paymentId") int paymentId) throws DataNotFoundException {
		Payment payment = service.getPayment(paymentId);
		if (payment == null) {
			logger.warn("payment ID Not found");
			throw new DataNotFoundException("Payment Transaction Not Found By these ID");
		}
		logger.info("payment Details return Successfully");
		return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}
	/*
	 * Get all Payment Details
	 */

	@GetMapping("/getAllPayment")
	public ResponseEntity<List<Payment>> getPayment() throws DataNotFoundException {
		List<Payment> payment = service.getAllPayment();
		if (payment.isEmpty()) {
			logger.warn("payment Details not found");
			throw new DataNotFoundException("Payment Transaction Not Found");
		}
		logger.info("payment Details return Successfully");
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}


	/*
	 * Update Payment Details
	 */
	@PutMapping("/updatePayment/{userId}")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment,@PathVariable("userId") int userId) throws DataNotFoundException {
		Payment paymentInfo = service.updatePayment(payment,userId);
		if (paymentInfo == null) {
			logger.warn("payment Data not found to Update");
			throw new DataNotFoundException("Payment Transaction Data Not Found for update");
		}
		logger.info("payment Details Updated Successfully");
		return new ResponseEntity<Payment>(paymentInfo, HttpStatus.OK);
	}

	/*
	 * Delete Payment Details
	 */
	@DeleteMapping("/deletePayment/{paymentId}/{userId}")
	public ResponseEntity<List<Payment>> deletePayment(@PathVariable("paymentId") int paymentId,
			@PathVariable("userId") int userId) {
		List<Payment> payment = service.deletePayment(paymentId,userId);
		logger.info("payment Details of user "+userId+" delete Successfully with payment ID "+paymentId);
		return new ResponseEntity<List<Payment>>(payment, HttpStatus.OK);
	}

}
