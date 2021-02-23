package com.example.employee.exceptions;

/**
 * {@code NoEmployeesAvailableException} is a subclass of RuntimeExceptions
 *
 * It occurs when there is no available employee
 *
 * @author  Kartik Jain
 * @since   1.0
 */
public class NoEmployeesAvailableException  extends RuntimeException {


	/**
	 * Generated Serial Version Id 
	 */
	private static final long serialVersionUID = 6062177947091590187L;

	/**
	 * Constructor to initialize the message for the Exception
	 * @param errorMessage Error message
	 */
	public NoEmployeesAvailableException(String errorMessage) {
	        super(errorMessage);
	    }


	

}
