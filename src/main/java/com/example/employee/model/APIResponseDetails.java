package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


/**
* This is the Request body template that handles the error scenarios.
* 
* @author Kartik Jain
* 
*/
public class APIResponseDetails {
	
	/**
	 * The Timestamp at which the response is generated
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	/**
	 * The HTTP status code for the response 
	 */
	private int status;
	
	/**
	 * The specific message for the response 
	 */
	private String message;
	
	/**
	 * The endpoint which was hit 
	 */
	private String path;

	/**
	 * <p>Private constructor used initializing timestamp
	 * </p>
	 * @since 1.0
	 */
	private APIResponseDetails() {
		timestamp = LocalDateTime.now();
	}

	/**
	 * <p>Constructor to create APIErrorDetails Object 
	 * </p>
	 * @param message message to be returned
	 * @param path URL that is requested
	 * @param status HTTP response code
	 */
	public APIResponseDetails(String message, String path, int status ) {
		this();
		this.message = message;
		this.path = path;
		this.status = status;
	}
	
	/**
	 * Getter for timestamp
	 * @return timestamp for the response
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Getter for message
	 * @return message for the response
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Getter for URL path that was called by the request
	 * @return URL Path that was called
	 */
	public String getPath() {
		return path;
	}
	/**
	 * Getter for HTTP status
	 * @return HTTP status for the response
	 */
	public int getStatus() {
		return status;
	}
}
