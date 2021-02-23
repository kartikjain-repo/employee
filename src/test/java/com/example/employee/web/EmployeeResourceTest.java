package com.example.employee.web;

import com.example.employee.TestUtils;
import com.example.employee.exceptions.EmployeeIdNotFoundException;
import com.example.employee.exceptions.NoEmployeesAvailableException;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeDTO;
import com.example.employee.model.EmployeePatchDTO;
import com.example.employee.model.State;
import com.example.employee.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeseResource.class)
@RunWith(SpringRunner.class)
public class EmployeeResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;

	private final String URL = "/v1/employee";
	private final String URL_WITH__INVALID_PARAM = URL + "/10";
	private final String URL_WITH_PARAM = URL + "/1";
	private Employee employee1;
	private Employee employee2;
	private List<Employee> list = new ArrayList<>();

	@Before
	public void setup() {
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
	public void testFindAll_withDataAvailable() throws Exception  {
		when(employeeService.findAll()).thenReturn(list);
		mockMvc.perform( MockMvcRequestBuilders
				.get(URL)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("[0].employeeId").value("EMP01"))
		.andExpect(jsonPath("[1].employeeId").value("EMP02"));

	}

	@Test
	public void testFindAll_withDataNotAvailable() throws Exception  {
		when(employeeService.findAll()).thenThrow(NoEmployeesAvailableException.class);
		mockMvc.perform( MockMvcRequestBuilders
				.get(URL)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("path").value("uri=" + URL));

	}

	@Test
	public void testAddEmployee_withValidData() throws Exception  {
		EmployeeDTO employeeDTO = new EmployeeDTO(employee1) ;
		when(employeeService.save(any(EmployeeDTO.class))).thenReturn(employee1);
		mockMvc.perform( MockMvcRequestBuilders
				.post(URL)
				.content(TestUtils.asJsonString(employeeDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("employeeId").value("EMP01"))
		.andExpect(jsonPath("state").value("ADDED"));

	}

	@Test
	public void testAddEmployee_withoutData() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	@Test
	public void testAddEmployee_withInvalidData() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.post(URL)
				.content(TestUtils.asJsonString(new EmployeePatchDTO()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	@Test
	public void testFindById_withValidData() throws Exception {
		when(employeeService.findById(anyLong())).thenReturn(employee1);
		mockMvc.perform( MockMvcRequestBuilders
				.get(URL_WITH_PARAM)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(TestUtils.asJsonString(employee1)));

	}

	@Test
	public void testFindById_withInvalidData() throws Exception {
		when(employeeService.findById(anyLong())).thenThrow(EmployeeIdNotFoundException.class);
		mockMvc.perform( MockMvcRequestBuilders
				.get(URL_WITH__INVALID_PARAM)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

	@Test
	public void testUpdate_withValidData() throws Exception {
		employee1.setState(State.ACTIVE);
		when(employeeService.update(anyString(),anyLong())).thenReturn(employee1);
		mockMvc.perform( MockMvcRequestBuilders
				.patch(URL_WITH_PARAM)
				.content(TestUtils.asJsonString(new EmployeePatchDTO("ADDED")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(TestUtils.asJsonString(employee1)));

	}

	@Test
	public void testUpdate_withInvalidEmployeeId() throws Exception {
		when(employeeService.update(anyString(),anyLong())).thenThrow(EmployeeIdNotFoundException.class);
		mockMvc.perform( MockMvcRequestBuilders
				.patch(URL_WITH_PARAM)
				.content(TestUtils.asJsonString(new EmployeePatchDTO("ADDED")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());

	}

}
