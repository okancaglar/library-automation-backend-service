package pia.task.library_automation.service;

import pia.task.library_automation.dto.LoginEmployeeDTO;
import pia.task.library_automation.entity.Employee;

import java.util.List;

public interface EmployeeService {
	Employee getEmployeeByAuthentication(int id, String password);
	Employee getEmployeeById(int id);
	List<Employee> getEmployees();
	void createEmployee(int id, String password, String firstName, String lastName, double dailyWage, String role);
	void updateEmployee(int id, String password, String firstName, String lastName, double dailyWage, String role);
	void deleteEmployee(int id);
	Employee authenticate(LoginEmployeeDTO employeeLogin);
}
