package com.ems.daoTest;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.ems.dao.EmployeeDao1;
import com.ems.model.Employee;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)

public class DaoTest {
	@InjectMocks
	EmployeeDao1 dao;
	

	@Mock
	JdbcTemplate template;
	@Captor
	private ArgumentCaptor<PreparedStatementSetter> preparedStatementSetterCaptor;

	@Mock
	private ResultSet resultSet;

	@SuppressWarnings("rawtypes")
	@Captor
	ArgumentCaptor<RowMapper> employeeRowMappper;
	//@Captor
	//ArgumentCaptor<EmployeeMapper> empMapper;

	@Test
	void testUpdateEmployee() throws SQLException {
		int id = 1;
		Employee employee = new Employee();

		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");
		dao.updateEmployee(id, employee);
		verify(template).update(Mockito.anyString(), preparedStatementSetterCaptor.capture());
		PreparedStatementSetter psSetter = preparedStatementSetterCaptor.getValue();
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		psSetter.setValues(ps);
		verify(ps).setString(1, employee.getName());
		verify(ps).setInt(2, employee.getSalary());
		verify(ps).setInt(3, employee.getAge());
		verify(ps).setString(4, employee.getAddress());
		verify(ps).setString(5, employee.getRole());
		verify(ps).setInt(6, id);

	}

	@Test
	void testCreateEmployee() throws SQLException {
		Employee employee = new Employee();
		employee.setName("vaishu");
		employee.setSalary(23000);
		employee.setAge(23);
		employee.setAddress("vijayawada");
		employee.setRole("sa");
		dao.createEmployee(employee);
		verify(template).update(Mockito.anyString(), preparedStatementSetterCaptor.capture());
		PreparedStatementSetter psSetter = preparedStatementSetterCaptor.getValue();
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		psSetter.setValues(ps);
		verify(ps).setString(1, employee.getName());
		verify(ps).setInt(2, employee.getSalary());
		verify(ps).setInt(3, employee.getAge());
		verify(ps).setString(4, employee.getAddress());
		verify(ps).setString(5, employee.getRole());

	}

	@SuppressWarnings("unchecked")
	@Test
	void testGetAllEmployees() throws SQLException {
		when(resultSet.getInt(1)).thenReturn(1);
		dao.getAllEmployees();
		verify(template).query(Mockito.anyString(), preparedStatementSetterCaptor.capture(),
				employeeRowMappper.capture());
		PreparedStatementSetter psSetter = preparedStatementSetterCaptor.getValue();
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		psSetter.setValues(ps);
		RowMapper<Employee> mapper = employeeRowMappper.getValue();
		Employee employee = mapper.mapRow(resultSet, 1);
		assertNotNull(employee);
	}

	@Test
	void testDeleteEmployee() throws SQLException {
		int id = 1;

		dao.deleteEmployee(id);
		verify(template).update(Mockito.anyString(), preparedStatementSetterCaptor.capture());
		PreparedStatementSetter psSetter = preparedStatementSetterCaptor.getValue();
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		psSetter.setValues(ps);
		verify(ps).setInt(1, id);
		assertNotNull(id);

	}
	@SuppressWarnings("unchecked")
	@Test
	void testGetEmployeeById() throws SQLException{
		when(resultSet.getInt(1)).thenReturn(1);
		int id = 1;
		dao.getEmployeeById(id);
		verify(template).query(Mockito.anyString(),preparedStatementSetterCaptor.capture(),employeeRowMappper.capture());
		PreparedStatementSetter psSetter = preparedStatementSetterCaptor.getValue();
		PreparedStatement ps = Mockito.mock(PreparedStatement.class);
		psSetter.setValues(ps);
		verify(ps).setInt(1, id);
		RowMapper<Employee> mapper = employeeRowMappper.getValue();
		Employee  emp = mapper.mapRow(resultSet,1);
		assertNotNull(emp);
	}

}
