package com.ems.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.ems.controller.EMSController;
import com.ems.model.Employee;
import com.ems.model.Response;
import com.ems.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)

public class ControllerTest {
	@InjectMocks
	private EMSController employeeController;

	@Mock
	private EmployeeService service;

	

	@Test
	void testcreateEmployee() throws SQLException {

		Employee employee = new Employee();
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");

		Mockito.when(service.createEmployee(Mockito.any())).thenReturn(1);
		ResponseEntity<Employee> actualResult = employeeController.createEmployee(employee);
		assertNotNull(actualResult);
		assertEquals(200, actualResult.getStatusCodeValue());
		verify(service, times(1)).createEmployee(employee);

	}

	@Test
	void testUpdateEmployee_Success() throws SQLException {
		int id = 1;
		Employee employee = new Employee();
employee.setId(id);
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");
		Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(employee);
		ResponseEntity<Response> actualResult = employeeController.updateEmployee(employee, id);
assertEquals(employee.getId(),id);

		assertNotNull(actualResult);
		assertEquals(200, actualResult.getStatusCodeValue());

	}
	@Test
	void testUpdateEmployee_NotFound() throws SQLException {
		int id = 1;
		Employee employee = new Employee();
employee.setId(id);
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");
		Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<Response> actualResult = employeeController.updateEmployee(employee, id);
assertEquals(employee.getId(),id);

		assertNotNull(actualResult);
		assertEquals(404, actualResult.getStatusCodeValue());

	}

	@Test
	public void testFindAll_Success() {

		Employee employee = new Employee(1, "aishu", 25000, 23, "vijayawada", "sa");
		Employee employee1 = new Employee(2, "vaishu", 25000, 23, "vijayawada", "sa");
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		employeeList.add(employee1);

		Mockito.when(service.getAllEmployees()).thenReturn(employeeList);

		ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		verify(service, times(1)).getAllEmployees();
		assertEquals(2, service.getAllEmployees().size());

		assertThat(service.getAllEmployees().get(0).getName()).isEqualTo(employee.getName());
		assertThat(service.getAllEmployees().get(1).getName()).isEqualTo(employee1.getName());

	}

	@Test
	public void testFindAll_NoContent() {

		List<Employee> employeeList = new ArrayList<>();

		Mockito.when(service.getAllEmployees()).thenReturn(employeeList);

		ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
		verify(service, times(1)).getAllEmployees();
		assertEquals(0, service.getAllEmployees().size());

	}

	@Test
	public void testFindById_success() {

		Employee employee = new Employee(1, "aishu", 25000, 23, "vijayawada", "sa");

		Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(1);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		verify(service, times(1)).getEmployeeById(1);

		assertThat(service.getEmployeeById(1).getName()).isEqualTo(employee.getName());
		assertThat(service.getEmployeeById(1).getSalary()).isEqualTo(employee.getSalary());
		assertThat(service.getEmployeeById(1).getAge()).isEqualTo(employee.getAge());
		assertThat(service.getEmployeeById(1).getAddress()).isEqualTo(employee.getAddress());
		assertThat(service.getEmployeeById(1).getRole()).isEqualTo(employee.getRole());

	}

	@Test
	public void testFindById_NotFound() {

		Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(null);

		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(1);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
		verify(service, times(1)).getEmployeeById(1);

	}

	/*
	 * @Test public void deleteById_Success() { int id = 1; Employee employee = new
	 * Employee(); employee.setId(id); employee.setName("vaishu");
	 * employee.setSalary(23000); employee.setAge(23);
	 * employee.setAddress("vijayawada"); employee.setRole("sa");
	 * Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(employee);
	 * 
	 * ResponseEntity<Response> responseEntity =
	 * employeeController.deleteEmployee(id);
	 * 
	 * assertThat(responseEntity.getStatusCodeValue()).isEqualTo(410);
	 * 
	 * }
	 */
	/*
	 * @Test public void deleteById_Failure() { int id = 1; Employee employee = new
	 * Employee(); employee.setId(id); employee.setName("vaishu");
	 * employee.setSalary(23000); employee.setAge(23);
	 * employee.setAddress("vijayawada"); employee.setRole("sa");
	 * Mockito.when(service.getEmployeeById(Mockito.anyInt())).thenReturn(null);
	 * 
	 * ResponseEntity<Response> responseEntity =
	 * employeeController.deleteEmployee(id);
	 * 
	 * assertThat(responseEntity.getStatusCodeValue()).isEqualTo(410);
	 * 
	 * }
	 */

}
