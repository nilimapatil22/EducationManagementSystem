package com.cg.boot.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.Payment;
import com.cg.boot.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService implements IPaymentService {

	@Autowired
	PaymentRepository repository;
	@Autowired
	UserService userService;
	
	@Override
	public Payment getPayment(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Payment> getAllPayment() {
		return repository.findAll();
	}

	/*
	 * 
	 * Add Payment Details
	 */
	@Override
	public Payment addPayment(Payment payment) throws DataNotFoundException {
    	userService.validateStudentId(payment.getStudentId());

		if (!isvalidStatus(payment.getPaymentStatus())) {
			throw new DataNotFoundException("Enter valid Status Details");
		}
		if (!isvalidType(payment.getPaymentType())) {
			throw new DataNotFoundException("Enter valid Transaction Type Details either credit or debit");

		}
		if (!isvaliAccountNumber(payment.getAccountNo())) {
			throw new DataNotFoundException("Enter Valid Account Number");
		}
		return repository.save(payment);
	}

	/*
	 * Validate PaymentStatus
	 */
	public boolean isvalidStatus(String Status) {
		boolean flag = false;
		if (!(Status.equals("successful") || (Status.equals("unsuccessful")))) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/*
	 * validate payment type
	 */
	public boolean isvalidType(String Type) {
		boolean flag = false;
		if (!(Type.equals("credit") || (Type.equals("debit")))) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	/*
	 * validate Account Number
	 */
	public boolean isvaliAccountNumber(long AccountNo) throws DataNotFoundException {
		String bank = String.valueOf(AccountNo);
		String regex = "[0-9]{9,18}";
		boolean flag = false;
		if (Pattern.matches(regex, bank)) {
			flag = true;
		} else {
			throw new DataNotFoundException("Invalid Account Number");
		}

		return flag;
	}

	/*
	 * Update Payment Details
	 */
	@Override
	public Payment updatePayment(Payment payment,int userId) throws DataNotFoundException {
		userService.validateAdminId(userId);
    	userService.validateStudentId(payment.getStudentId());

		Payment paymentDetails = getPayment(payment.getPaymentId());

		if (paymentDetails != null) {

			if (!isvalidStatus(payment.getPaymentStatus())) {
				throw new DataNotFoundException("Enter valid Status Details");
			}

			if (!isvalidType(payment.getPaymentType())) {
				throw new DataNotFoundException("Enter valid Transaction Type Details either credit or debit");

			}
			if (!isvaliAccountNumber(payment.getAccountNo())) {
				throw new DataNotFoundException("Enter Valid Account Number");
			}
			paymentDetails = repository.save(payment);
		}
		return paymentDetails;
	}

	/*
	 * Delete Payment Details
	 */
	@Override
	public List<Payment> deletePayment(int paymentId,int userId) {
		userService.validateAdminId(userId);
		repository.deleteById(paymentId);
		return repository.findAll();
	}

}
