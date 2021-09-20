package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.model.Employee;
import com.ems.model.Response;
import com.ems.service.EmployeeService;

@RestController

public class EMSController {
	@Autowired
	public EmployeeService service;

	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employee = service.getAllEmployees();
		if (employee.isEmpty()) {

			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = service.getEmployeeById(id);
		if (employee == null) {

			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

		int Emp = service.createEmployee(employee);

		return new ResponseEntity<Employee>(HttpStatus.OK);

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
		var responsePojo = new Response();
		Employee existingEmp = service.getEmployeeById(id);
		if (existingEmp == null) {
			responsePojo.setCode(HttpStatus.NOT_FOUND.toString());
			responsePojo.setMessage("employee  not found! Please enter an exixting employeeid to modify");
			return new ResponseEntity<>(responsePojo, HttpStatus.NOT_FOUND);

		} else {
			service.updateEmployee(id, employee);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@DeleteMapping(path = "/employees/{id}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable("id") Integer id) {
		var responsePojo = new Response();
		Employee employee = service.getEmployeeById(id);
		if (employee == null) {
			responsePojo.setCode(HttpStatus.GONE.toString());
			responsePojo.setMessage("employee Sucessfully Deleted");
			return new ResponseEntity<>(responsePojo, HttpStatus.GONE);

		} else {

			service.deleteEmployee(id);
			responsePojo.setMessage("employee Sucessfully Deleted");
			return new ResponseEntity<>(responsePojo, HttpStatus.GONE);
		}
	}
}
