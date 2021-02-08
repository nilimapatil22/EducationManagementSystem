package com.cg.boot.exceptions;

/**
 * @author Priyanka. This class contains customized exception
 *         DataNotFoundException which extends RuntimeException. It contains
 *         default and parameterized constructor.
 *         It throws an exception with customized message.
 * 
 */
@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
	public DataNotFoundException() {

	}

	public DataNotFoundException(String message) {
		super(message);
	}
}
