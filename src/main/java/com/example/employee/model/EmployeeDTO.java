package com.example.employee.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object(DTO) class to handle data from rest endpoints and creating employee Object
 * @author Kartik Jain
 * @since 1.0
 */
@Data
@NoArgsConstructor
public class EmployeeDTO {


	@ApiModelProperty(notes="The database generated id for the record", example="1")
	private Long id;

	@ApiModelProperty(notes="The application specific employee id", example="EMP01", required = true)
	@NotNull
	private String employeeId;

	@ApiModelProperty(notes="The name of the employee", example="John Doe", required = true)
	@NotNull
	private String name;

	@ApiModelProperty(notes="The contract information for the employee", example ="permanent", required = true)
	@NotNull
	private String contractInformation;

	@ApiModelProperty(notes="The department for the employee", example = "Accounts", required = true)
	@NotNull
	private String department;

	@ApiModelProperty(notes="The age of the employee", example = "22", required = true)
	@NotNull
	private int age;

	@ApiModelProperty(notes="The State amongst ADDED,CHECK-IN,APPROVED,ACTIVE", example = "ADDED")
	private String state;

	/**
	 * Constructor to initialise EmployeeDTO from Employee Object
	 * @param employee
	 */
	public EmployeeDTO(Employee employee){
		this.age = employee.getAge();
		this.contractInformation = employee.getContractInformation();
		this.department = employee.getDepartment();
		this.employeeId = employee.getEmployeeId();
		this.id = employee.getId();
		this.name = employee.getName();
		this.state = employee.getState().getActualValue();
	}

	/**
	 * Method to convert employeeeDTO to Employee object for interactions with database
	 * @return
	 */
	public Employee toEmployee() {
		Employee employee =  new Employee();
		employee.setAge(getAge());
		employee.setContractInformation(getContractInformation());
		employee.setDepartment(getDepartment());
		employee.setEmployeeId(getEmployeeId());
		employee.setName(getName());
		return employee;
	}
}
