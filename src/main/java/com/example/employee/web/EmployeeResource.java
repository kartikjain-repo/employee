package com.example.employee.web;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.EmployeePatchDTO;
import com.example.employee.service.EmployeeService;
import com.example.employee.utils.SwaggerStringConstants;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for Employee Resource
 * @author Kartik Jain
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(value = "Employee API")
@RequestMapping("/v1/employee")
public class EmployeeResource {


	private EmployeeService employeeService;

	/**
	 * Constructor for dependency injection
	 * @param employeeService Service implementation for CRUD operations
	 */
	@Autowired
	public EmployeeResource(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	/**
	 * Controller method for retrieving all employees in database
	 * @return List of all available employees
	 */
	@ApiOperation(value = SwaggerStringConstants.FIND_ALL_DESCRIPTION, response = EmployeeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = SwaggerStringConstants.FIND_ALL_SUCCESS_MESSAGE +"\t\n" + SwaggerStringConstants.EMPLOYEE_NOT_FOUND_MESSAGE)
			})
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<Object> findAllEmployees() {
		ResponseEntity<Object> response;
		List<Employee> employeeList = employeeService.findAll();
		List<EmployeeDTO> empDTOList = employeeList.stream().map(EmployeeDTO :: new).collect(Collectors.toList());
		response = new ResponseEntity<>(empDTOList, HttpStatus.OK);
		return response;
	}
	
	/**
	 * Controller method for getting single employee corresponding to an id
	 * @param id unique value for retrieving employee
	 * @return employee information for employee corresponding to the id provided
	 */
	@ApiOperation(value = SwaggerStringConstants.FIND_BY_ID_DESCRIPTION, response = EmployeeDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = SwaggerStringConstants.FIND_BY_ID_SUCCESS_MESSAGE),
			@ApiResponse(code = 400, message = SwaggerStringConstants.EMPLOYEE_ID_DOES_NOT_EXIST) })
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Object> findEmployeeById(
			@ApiParam(value = SwaggerStringConstants.ID_DESCRIPTION_VIEW, required = true) @PathVariable Long id) {

		ResponseEntity<Object> response;
		Employee employee = employeeService.findById(id);
		response = new ResponseEntity<>(new EmployeeDTO(employee), HttpStatus.OK);
		return response;
	}

	/**
	 * Controller method for creating a new employee
	 * @param employeeDTO information related to the employee that needs to be saved
	 * @return saved employee
	 */
	@ApiOperation(value = SwaggerStringConstants.CREATE_NEW_DESCRIPTION, response = EmployeeDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = SwaggerStringConstants.CREATE_NEW_SUCCESS_MESSAGE),
			@ApiResponse(code = 400, message = SwaggerStringConstants.INVALID_PAYLOAD_MESSAGE) })
	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> createNewEmployee(
			@ApiParam(name = "body", value = SwaggerStringConstants.REQUEST_BODY_DESCRIPTION, required = true) @Valid @RequestBody EmployeeDTO employeeDTO) {

		ResponseEntity<Object> response;
		Employee savedEmployee = employeeService.save(employeeDTO);
		response = new ResponseEntity<>(new EmployeeDTO(savedEmployee), HttpStatus.CREATED);
		return response;
	}

	/**
	 * Controller method for updating existing employee in the application
	 * @param state employee state that needs to be updated
	 * @param id id of the employee that needs to be updated
	 * @return employee with updated information
	 */
	@ApiOperation(value = SwaggerStringConstants.UPDATE_EMPLOYEE_DESCRIPTION, response = EmployeeDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = SwaggerStringConstants.UPDATE_EMPLOYEE_SUCCESS_MESSAGE),
			@ApiResponse(code = 400, message = SwaggerStringConstants.EMPLOYEE_ID_DOES_NOT_EXIST + "\n\t"+ SwaggerStringConstants.INVALID_PAYLOAD_MESSAGE) })

	@PatchMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeePatchDTO state, @PathVariable Long id) {
		ResponseEntity<Object> response;
		Employee updatedEmployee = employeeService.update(state.getState(), id);
		response = new ResponseEntity<>(new EmployeeDTO(updatedEmployee), HttpStatus.OK);
		return response;
	}

}