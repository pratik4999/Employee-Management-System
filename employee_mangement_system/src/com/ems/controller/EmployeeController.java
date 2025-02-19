package com.ems.controller;

import java.util.List;
import java.util.Scanner;

import com.ems.exception.DatabaseException;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;

import service.EmployeeService;

public class EmployeeController {
	private final EmployeeService service;
	private final Scanner scanner;

	public EmployeeController(EmployeeService service) {
		this.service = service;
		this.scanner = new Scanner(System.in);
	}

	public void start() {
		while (true) {
			System.out.println("\n===== Employee Management System =====");
			System.out.println("1. Add Employee | 2. Update | 3. Delete | 4. View All | 5. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			try {
				switch (choice) {
				case 1 -> addEmployee();
				case 2 -> updateEmployee();
				case 3 -> deleteEmployee();
				case 4 -> viewAllEmployees();
				case 5 -> {
					System.out.println("Exiting... Thank you!");
					scanner.close();
					return;
				}
				default -> System.out.println("Invalid option! Try again.");
				}
			} catch (DatabaseException  | EmployeeNotFoundException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	private void addEmployee() {
		System.out.print("Enter Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Department: ");
		String department = scanner.nextLine();
		System.out.print("Enter Salary: ");
		double salary = scanner.nextDouble();

		service.getEmployeeDAO().addEmployee(new Employee(0, name, department, salary));
		System.out.println("Employee added!");
	}

	private void updateEmployee() {
		System.out.print("Enter Employee ID: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		System.out.print("Enter New Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter New Department: ");
		String department = scanner.nextLine();
		System.out.print("Enter New Salary: ");
		double salary = scanner.nextDouble();

		service.getEmployeeDAO().updateEmployee(new Employee(id, name, department, salary));
		System.out.println("Employee updated!");
	}

	private void deleteEmployee() {
		System.out.print("Enter Employee ID: ");
		int id = scanner.nextInt();
		service.getEmployeeDAO().deleteEmployee(id);
		System.out.println("Employee deleted!");
	}

	private void viewAllEmployees() {
		List<Employee> employees = service.getEmployeeDAO().getAllEmployees();
		if (employees.isEmpty()) {
			System.out.println("No employees found.");
		} else {
			employees.forEach(System.out::println);
		}

	}

}
