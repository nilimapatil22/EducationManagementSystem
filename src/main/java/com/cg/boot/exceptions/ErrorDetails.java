package com.cg.boot.exceptions;

import java.time.LocalDate;

/**
 * @author Priyanka. This class includes declaration of parameters of ErrorDetails
 *         class, default constructor, parameterized constructors, getter and
 *         setter methods of parameters and toString method to display.
 *
 */
public class ErrorDetails {

	private String message;
	private LocalDate date;
	private String details;

	public ErrorDetails() {
		
	}

	public ErrorDetails(String message, LocalDate date, String details) {
		super();
		this.message = message;
		this.date = date;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
