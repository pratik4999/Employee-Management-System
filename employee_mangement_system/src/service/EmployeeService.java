package service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Predicate;

import com.ems.dao.EmployeeDAO;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;

public class EmployeeService {
	  private final EmployeeDAO  employeeDAO;

	    public EmployeeService(EmployeeDAO employeeDAO) {
	        this.employeeDAO = employeeDAO;
	    }

	    public EmployeeDAO getEmployeeDAO() {
	        return employeeDAO;
	    }


}
