package com.example.employee.exceptions;


import com.example.employee.model.APIResponseDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * This is the ControllerAdvice for the EmployeeResource. This handles all the internal exceptions and prepare corresponding
 * responses
 * @author Kartik Jain
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handler for InvalidStateValueException
	 * @param ex the exception Passed
	 * @param request the Web request Object
	 * @return response to be returned
	 */
	@ExceptionHandler(InvalidStateValueException.class )
	public ResponseEntity<Object> badRequestExceptionHandler(InvalidStateValueException ex, WebRequest request) {
		APIResponseDetails errorDetails = new APIResponseDetails( ex.getMessage(), request.getDescription(false), 400);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}


	/**
	 * Handler for NoEmployeesAvailableException
	 * @param ex the exception Passed
	 * @param request the Web request Object
	 * @return response to be returned
	 */
	@ExceptionHandler(NoEmployeesAvailableException.class )
	public ResponseEntity<Object> employeesNotFoundHandler(NoEmployeesAvailableException ex, WebRequest request) {

		APIResponseDetails errorDetails = new APIResponseDetails( ex.getMessage(), request.getDescription(false), 200);
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}
	
	/**
	 * Handler for EmployeeIdNotFoundException
	 * @param ex the exception Passed
	 * @param request the Web request Object
	 * @return response to be returned
	 */
	@ExceptionHandler(EmployeeIdNotFoundException.class )
	public ResponseEntity<Object> employeeIdNotFoundHandler(EmployeeIdNotFoundException ex, WebRequest request) {

		APIResponseDetails errorDetails = new APIResponseDetails( ex.getMessage(), request.getDescription(false), 400);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}