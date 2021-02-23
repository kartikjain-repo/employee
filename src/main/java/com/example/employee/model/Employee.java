package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class for Employee  used by JPA to persist data
 * @author Kartik Jain
 * @since 1.0
 */

@Entity
@Data
@NoArgsConstructor
@JsonRootName("employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String employeeId;

	private String name;

	private String contractInformation;

	private String department;

	private int age;

	private State state;

	public Employee(String employeeId, String name, String contractInformation, String department, int age){
		this.age = age;
		this.employeeId = employeeId;
		this.name = name;
		this.contractInformation = contractInformation;
		this.department = department;
	}

}
