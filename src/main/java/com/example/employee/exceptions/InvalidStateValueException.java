package com.example.employee.exceptions;

/**
 *
 * Exception which occurs when an invalid value for state is provided
 *
 * @author  Kartik Jain
 * @since   1.0
 */
public class InvalidStateValueException extends RuntimeException {


	/**
	 * Generated Serial Version Id
	 */
	private static final long serialVersionUID = 6062177947091590187L;

	/**
	 * Constructor to initialize the message for the Exception
	 * @param errorMessage Error message
	 */
	public InvalidStateValueException(String errorMessage) {
	        super(errorMessage);
	    }


	

}
