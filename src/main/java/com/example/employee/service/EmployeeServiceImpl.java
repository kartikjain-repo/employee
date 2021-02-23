package com.example.employee.service;

import com.example.employee.exceptions.EmployeeIdNotFoundException;
import com.example.employee.exceptions.InvalidStateValueException;
import com.example.employee.exceptions.NoEmployeesAvailableException;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.State;
import com.example.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * This is the service class for interacting with JPARepository to perform operation on employees
 * @author Kartik Jain
 * @since 1.0
 */
@Service
@Slf4j
@Qualifier("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;

	/**
	 * Constructor for dependency injection
	 * @param employeeRepository Repository bean to be injected
	 */
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/**
	 * Service method implementation for fetching all available employees. When there  is no employees available,
	 * NoEmployeesAvailableException is thrown
	 * @return List of all available employees
	 */
	@Override
	public List<Employee> findAll()   {
		log.info("Fetching the list of employees from database");
		List<Employee> employeeList = employeeRepository.findAll();
		if(employeeList.isEmpty()) {
			log.info("No Employees available to be fetched");
			throw new NoEmployeesAvailableException("No employees found");
		}
		return employeeList;
	}

	/**
	 * Service method implementation for fetching employee based on id. If the produc with requested id is not available,
	 * EmployeeIdNotFoundException is thrown
	 * @param id which needs to be fetched
	 * @return employee corresponding to the id
	 *
	 */
	@Override
	public Employee findById(long id) {
		log.info("Fetching employee information for {}", id);
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isEmpty()) {
			log.error("Employee not available for {}", id);
			throw new EmployeeIdNotFoundException(String.format("Employee with id %s has been deleted", id));
		}
		log.debug("Fetched Employee information :{}",employee);
		return employee.get();
	}

	/**
	 * Service method implementation for saving employee
	 * @param employeeDTO Object to be saved
	 * @return saved employee
	 */
	@Override
	public Employee save(EmployeeDTO employeeDTO) {
		Employee employee = employeeDTO.toEmployee();
		// Initialising state as Added
		employee.setState(State.ADDED);
		log.debug("employee information to be saved :{}", employee );
		Employee savedEmployee = employeeRepository.save(employee);
		log.info("employee saved with id {}", savedEmployee.getId());
		return savedEmployee;
	}

	/**
	 * Service Method implementation for updating of employee
	 * @param state  Object containing values that needs to updated
	 * @param id value on basis of which employee would be fetched for updating
	 * @return updatedEmployee
	 */
	@Override
	public Employee update(String state, long id)  {

		log.info("updating Employee with id {} with values: {}",id,state);
		Employee existingEmployee = findById(id);
		if(State.contains(state)){
			existingEmployee.setState(State.getValue(state));
		}
		else{
			throw new InvalidStateValueException(state + " is not a valid value for state");
		}
		log.info("Updated employee information for employee Id: {} is {}",existingEmployee.getEmployeeId());
		return employeeRepository.save(existingEmployee);
	}
}
