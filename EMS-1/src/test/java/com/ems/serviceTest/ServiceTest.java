package com.ems.serviceTest;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ems.dao.EmployeeDao1;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)

public class ServiceTest {

	@InjectMocks
	EmployeeService service;
	
	@Mock
	EmployeeDao1 dao1;

	@Test
	void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");

		Mockito.when(dao1.createEmployee(employee)).thenReturn(1);

		assertEquals(1, service.createEmployee(employee));
	}

	// Employee employee = new Employee(1, "aishu", 25000, 23, "vijayawada", "sa");
	// Mockito.when(dao.createEmployee(employee)).thenReturn(employee);
	// assertEquals(employee, service.createEmployee(employee));

	// verify(service, times(1)).createEmployee(employee);

	@Test
	void testGetAllEmployees() {

		Mockito.when(dao1.getAllEmployees())
				.thenReturn(Stream.of(new Employee(1, "aishu", 25000, 23, "vijayawada", "sa"),
						new Employee(2, "vaishu", 25000, 23, "vijayawada", "sa")).collect(Collectors.toList()));
		assertEquals(2, service.getAllEmployees().size());// we will just compare with the size. as two entities we have
															// created size is 2
	}

	@Test
	void testGetEmployeeById() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");

		Mockito.when(dao1.getEmployeeById(Mockito.anyInt())).thenReturn(employee);
		assertEquals(employee, service.getEmployeeById(Mockito.anyInt()));

		Employee employee1 = service.getEmployeeById(1);

		assertEquals("vaishu", employee1.getName());
		assertEquals(23000, employee1.getSalary());
		assertEquals(23, employee1.getAge());
		assertEquals("vijayawada", employee1.getAddress());
		assertEquals("sa", employee1.getRole());

	}

	@Test
	void testDeleteEmployee() {
		int id = 1;

		Mockito.when(dao1.deleteEmployee(Mockito.anyInt())).thenReturn(id);

		assertEquals(id, service.deleteEmployee(Mockito.anyInt()));

	}

	@Test
	void testUpdateEmployee() {
		int id = 1;
		Employee employee = new Employee();
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");
		Mockito.when(dao1.updateEmployee(id, employee)).thenReturn(1);
		assertEquals(1, service.updateEmployee(id, employee));

	}

}
