package com.example.employee;

import com.example.employee.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static Employee stringToEmployee(String employeeJson) {
		Employee employee = null;
		try {
			employee = objectMapper.readValue(employeeJson, Employee.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
