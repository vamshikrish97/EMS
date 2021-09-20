package com.ems.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("employee_dataa")
//CREATE TABLE employee_table(id SERIAL , name VARCHAR(255), salary INT, age INT, address VARCHAR(255),role VARCHAR(255));
public class Employee {
private int id;
private String name;
private int salary;
private int age;
private String address;
private String role;



public Employee() {
	
}
public Employee(int id, String name, int salary, int age, String address, String role) {
	super();
	this.id = id;
	this.name = name;
	this.salary = salary;
	this.age = age;
	this.address = address;
	this.role = role;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", age=" + age + ", address=" + address
			+ ", role=" + role + "]";
}

}
