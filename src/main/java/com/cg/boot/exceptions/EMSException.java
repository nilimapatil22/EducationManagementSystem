package com.cg.boot.exceptions;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Priyanka. This class extends {@link ResponseEntityExceptionHandler}
 *         which is base class of {@link ControllerAdvice} that provide
 *         centralized exception handling across all methods through {@link ExceptionHandler} methods.
 *
 */
@ControllerAdvice
public class EMSException extends ResponseEntityExceptionHandler {
     /**
      * This method handle {@link DataNotFoundException} exception and return {@link ResponseEntity}.
      * @param exception : {@link DataNotFoundException}
      * @param request : {@link WebRequest}
      * @return {@link ResponseEntity} : details {@link ErrorDetails}, {@link HttpStatus}
      */
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleException(DataNotFoundException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
	/**
	 * This method handle all internal spring exceptions and return {@link ResponseEntity}
	 * @param exception : {@link Exception}
	 * @param request : {@link WebRequest}
	 * @return  {@link ResponseEntity} : details {@link ErrorDetails}, {@link HttpStatus} 
	 */

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<Object>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/**
	 * This method handle {@link MethodArgumentNotValidException} and return {@link ResponseEntity}
	 * @param ex : {@link MethodArgumentNotValidException}
	 * @param headers : {@link HttpHeaders}
	 * @param status : {@link HttpStatus}
	 * @param request : {@link WebRequest}
	 * @return  {@link ResponseEntity} : errors {@link HashMap}, {@link HttpStatus}
	 */

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

}
