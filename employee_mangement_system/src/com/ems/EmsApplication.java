package com.ems;

import com.ems.controller.EmployeeController;
import com.ems.dao.EmployeeDAOImpl;

import service.EmployeeService;

public class EmsApplication {

	public static void main(String[] args) {

		EmployeeService service = new EmployeeService(new EmployeeDAOImpl());
		EmployeeController controller = new EmployeeController(service);
		controller.start();
	}
}
