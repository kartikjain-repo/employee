package com.example.employee.utils;

/**
 * Class containing messages and description for Swagger UI
 * @author Kartik Jain
 *
 */
public final class SwaggerStringConstants {
	/**
	 * private constructor to prevent SwaggerStringConstants to be instantiated
	 */
	private SwaggerStringConstants() {
	}
	
	/**
	 * Application description
	 */
	public static final String APPLICATION_DESCRIPTION = "APIs to cater to different operations on employee";
	
	/**
	 * Find All API description
	 */
	public static final String FIND_ALL_DESCRIPTION = "View a list of available employees";
	
	/**
	 * Find All API Success Message
	 */
	public static final String FIND_ALL_SUCCESS_MESSAGE = "Successfully retrieved employee list";
	
	/**
	 * Find By id API description
	 */
	public static final String FIND_BY_ID_DESCRIPTION = "View a employee based on id";
	
	/**
	 * Find By Id API Success Message
	 */
	public static final String FIND_BY_ID_SUCCESS_MESSAGE = "Successfully retrieved employee for requested id";
	
	/**
	 * Employee Id doesnt not exist Message
	 */
	public static final String EMPLOYEE_ID_DOES_NOT_EXIST= "Requested employee id does not exist";
	
	/**
	 * Id field for find by id API parameter description
	 */
	public static final String ID_DESCRIPTION_VIEW = "Id of the employee that needs to be fetched";
	
	/**
	 * Create new Employee API Description
	 */
	public static final String CREATE_NEW_DESCRIPTION = "Add a employee";
	
	/**
	 * Create New Employee API success message
	 */
	public static final String CREATE_NEW_SUCCESS_MESSAGE = "Successfully added a employee";
	
	/**
	 * Invalid Payload Message
	 */
	public static final String INVALID_PAYLOAD_MESSAGE = "Request payload invalid";
	
	/**
	 * Request Body Description for create and update API
	 */
	public static final String REQUEST_BODY_DESCRIPTION = "Request body in JSON format...";
	
	/**
	 * Employee not found for get all message
	 */
	public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "There is no available Employees";
	
	/**
	 * Update Employee API Description
	 */
	public static final String UPDATE_EMPLOYEE_DESCRIPTION ="Update a employee's state";
	
	/**
	 * Update Employee API success message
	 */
	public static final String UPDATE_EMPLOYEE_SUCCESS_MESSAGE = "Employee Successfully updated";



}
