package com.ems.dao;

import java.util.List;

import com.ems.model.Employee;

public interface EmployeeDAO {

	    void addEmployee(Employee employee);
	    void updateEmployee(Employee employee);
	    void deleteEmployee(int id);
	    List<Employee> getAllEmployees();
	    
}
