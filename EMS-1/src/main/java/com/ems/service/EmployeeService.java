package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.EmployeeDao1;
import com.ems.model.Employee;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao1 dao1;
	
	public List<Employee> getAllEmployees(){
	

		return dao1.getAllEmployees();
	}
	
	public Employee getEmployeeById(int id) {
		return dao1.getEmployeeById(id);
	}
		public int updateEmployee(int id, Employee employee) {
			return dao1.updateEmployee(id, employee);
			
			
}
		public int createEmployee(Employee employee) {
			return dao1.createEmployee(employee);
			
		}
		public int deleteEmployee(int id) {
			return dao1.deleteEmployee(id);
		}
		
		//public Employee checkEmployeeExistOrNot( int id) {
			//return dao1.checkEmployeeExistOrNot(id);
			
		//}
		}