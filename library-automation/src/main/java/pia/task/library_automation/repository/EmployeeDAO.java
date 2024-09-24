package pia.task.library_automation.repository;

import pia.task.library_automation.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
	Employee getEmployeeByAuthentication(int id, String password);
	Employee getEmployeeById(int id);
	void updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
	void createEmployee(Employee employee);
	List<Employee> getEmployees();
}
