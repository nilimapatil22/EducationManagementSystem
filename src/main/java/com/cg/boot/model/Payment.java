package com.cg.boot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Nilima. This class includes declaration of parameters of payment
 *         class, default constructor, parameterized constructors, getter and
 *         setter methods of parameters and toString method to display.
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Payment_master")
public class Payment implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "payment_Id")
	private int paymentId;

	@NotNull(message = "Enter valid Fee Details")
	@Min(value = 1, message = "Enter valid Fee Details")
	private double amount;

	@Column(name = "payment_Type")
	private String paymentType;

	@Column(name = "payment_Status")
	private String paymentStatus;

	@NotNull(message = "Enter valid student ID")
	@Min(value = 1, message = "Enter valid student ID")
	@Column(name = "student_Id")
	private int studentId;

	
	@NotEmpty(message = "Date should not empty")
	@Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format")
	private String date;

	Payment() {

	}

	public Payment(
			@NotNull(message = "Enter valid Fee Details") @Min(value = 1, message = "Enter valid Fee Details") double amount,
			String paymentType, String paymentStatus,
			@NotNull(message = "Enter valid student ID") @Min(value = 1, message = "Enter valid student ID") int studentId,
			@NotEmpty(message = "Date should not empty") @Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format") String date) {
		super();
		this.amount = amount;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.studentId = studentId;
		this.date = date;
	}

	public Payment(int paymentId,
			@NotNull(message = "Enter valid Fee Details") @Min(value = 1, message = "Enter valid Fee Details") double amount,
			String paymentType, String paymentStatus,
			@NotNull(message = "Enter valid student ID") @Min(value = 1, message = "Enter valid student ID") int studentId,
			@NotEmpty(message = "Date should not empty") @Pattern(regexp = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])", message = "Date should be in yyyy-MM-dd format") String date) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.studentId = studentId;
		this.date = date;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", paymentType=" + paymentType
				+ ", paymentStatus=" + paymentStatus + ", studentId=" + studentId + ", date=" + date + "]";
	}

	
	
}
