package com.example.employee.service;

import com.example.employee.exceptions.EmployeeIdNotFoundException;
import com.example.employee.exceptions.InvalidStateValueException;
import com.example.employee.exceptions.NoEmployeesAvailableException;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.State;
import com.example.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;


class EmployeeServiceTest {
	@Mock
	private EmployeeRepository repository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	private Employee employee1,employee2;
	private List<Employee> list = new ArrayList<>();
	
	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
		employee1 = new Employee("EMP01","John Doe","permanent","Accounts",31);
		employee1.setState(State.ADDED);
		employee1.setId(1L);
		employee2 = new Employee("EMP02","Michael Smith","temporary","IT",25);
		employee2.setState(State.CHECK_IN);
		employee2.setId(2L);
		list.add(employee1);
		list.add(employee2);
	}

	@Test
	void findAll_WhenRecordPresent_ReturnList(){
		when(repository.findAll()).thenReturn(list);
		List<Employee> employeeList= employeeService.findAll();
		assertFalse(employeeList.isEmpty());
		assertEquals(2,employeeList.size());
		verify(repository,times(1)).findAll();
	}

	@Test
	void findAll_WhenRecordsNotPresent_ReturnList(){
		when(repository.findAll()).thenReturn(new ArrayList<Employee>());
		Assertions.assertThrows(NoEmployeesAvailableException.class, ()->employeeService.findAll());
	}

	@Test
	void findById_WhenRecordsIsPresent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee1));
		Assertions.assertEquals(employeeService.findById(1L), employee1);
	}

	@Test
	void findById_WhenRecordsIsNotPresent() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(EmployeeIdNotFoundException.class, ()->employeeService.findById(10L));
	}

	@Test
	void save_Successful() {
		when(repository.save(Mockito.any(Employee.class))).thenReturn(employee1);

		Assertions.assertEquals(employeeService.save(new EmployeeDTO(employee1)), employee1);
	}

	@Test
	void update_WhenRecordsIsPresent() {
		when(repository.save(Mockito.any(Employee.class))).thenReturn(employee2);
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee2));
		employee2.setState(State.ADDED);
		Assertions.assertEquals(employeeService.update("ACTIVE", 1l), employee2);
	}
	@Test
	void updateInvalidStateValue() {
		when(repository.save(Mockito.any(Employee.class))).thenReturn(employee2);
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee2));
		employee2.setState(State.ADDED);
		Assertions.assertThrows( InvalidStateValueException.class,()->employeeService.update("INVCA", 1l));
	}

	@Test
	void update_WhenRecordsIsNotPresent() {
		when(repository.save(Mockito.any(Employee.class))).thenReturn(employee1);
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(EmployeeIdNotFoundException.class, ()->employeeService.findById(1L));
	}
}
