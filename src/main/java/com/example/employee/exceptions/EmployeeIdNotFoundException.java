package com.example.employee.exceptions;

/**
 * {@code EmployeeIdNotFoundException} is a subclass of RuntimeExceptions
 *
 * It occurs when there is no available employee corresponding to a EmployeeId
 *
 * @author  Kartik Jain
 * @since   1.0
 */
public class EmployeeIdNotFoundException extends RuntimeException {


	/**
	 * Generated Serial Version Id 
	 */
	private static final long serialVersionUID = -1153436319129075299L;

	/**
	 * Constructor to initialize the message for the Exception
	 * @param errorMessage Error message
	 */
	public EmployeeIdNotFoundException(String errorMessage) {
	        super(errorMessage);
	    }

}
