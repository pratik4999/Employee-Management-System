package com.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ems.exception.DatabaseException;
import com.ems.model.Employee;
import com.ems.util.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) {
		String query = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";

		if (employee == null) {
			throw new IllegalArgumentException("Employee object cannot be null");
		}

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			;
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getDepartment());
			stmt.setDouble(3, employee.getSalary());
			stmt.executeUpdate();
		}

		catch (SQLException e) {
			throw new DatabaseException("Error adding employee: " + e.getMessage());
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		String query = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";

		if (employee == null) {
			throw new IllegalArgumentException("Employee object cannot be null");
		}

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			;
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getDepartment());
			stmt.setDouble(3, employee.getSalary());
			stmt.setDouble(4, employee.getId());
			int updated = stmt.executeUpdate();
			if (updated == 0)
				throw new DatabaseException("No employee found with ID: " + employee.getId());

		} catch (SQLException e) {
			throw new DatabaseException("Error adding employee: " + e.getMessage());
		}
	}

	@Override
	public void deleteEmployee(int id) {
		String query = "DELETE FROM employee WHERE id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);
			int deleted = stmt.executeUpdate();
			if (deleted == 0)
				throw new DatabaseException("No employee found with ID: " + id);
		} catch (SQLException e) {
			throw new DatabaseException("Error deleting employee: " + e.getMessage());
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		String query = "SELECT * FROM employee";
		List<Employee> employees = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"),
						rs.getDouble("salary")));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error retrieving employees: " + e.getMessage());
		}
		return employees;
	}

}
