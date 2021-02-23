package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;

import java.util.List;

/**
 * Service interface for defining the Service methods for employee
 * @author Kartik Jain
 * @since 1.0
 */
public interface EmployeeService {
	/**
	 * Declaration of method  to fetch all available Employees
	 * @return list of employees
	 */
	public List<Employee> findAll();

	/**
	 * Declaration of method to fetch employee by id
	 * @param id value to be used in fetching
	 * @return Employee having passed id
	 */
	public Employee findById(long id);

	/**
	 * Declaration of method to save employee
	 * @param employeeDTO Object to be saved
	 * @return saved employee
	 */
	public Employee save(EmployeeDTO employeeDTO);

	/**
	 * Declaration of method for updation of employee state
	 * @param employee Object containing values to be updated
	 * @param id value to identify employee for updation
	 * @return updated employee
	 */
	public Employee update(String employee, long id);


}
