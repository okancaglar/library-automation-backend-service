package pia.task.library_automation.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pia.task.library_automation.dto.LoginEmployeeDTO;
import pia.task.library_automation.entity.Employee;
import pia.task.library_automation.entity.Role;
import pia.task.library_automation.repository.EmployeeDAO;
import pia.task.library_automation.repository.RoleDAO;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService{

	private EmployeeDAO employeeRepository;
	private RoleDAO roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public EmployeeServiceImp(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleDAO roleRepository, EmployeeDAO employeeRepository) {
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee getEmployeeByAuthentication(int id, String password) {
		return employeeRepository.getEmployeeByAuthentication(id, password);
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.getEmployeeById(id);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.getEmployees();
	}

	@Transactional
	@Override
	public void createEmployee(int id, String password, String firstName, String lastName, double dailyWage, String roleName) {
		Role role = roleRepository.getRoleByName(roleName.toLowerCase());
		Employee employee = new Employee(id, passwordEncoder.encode(password), firstName, lastName, LocalDate.now(), dailyWage, role);
		employeeRepository.createEmployee(employee);
	}

	@Transactional
	@Override
	public void updateEmployee(int id, String password, String firstName, String lastName, double dailyWage, String roleName) {
		Role role = roleRepository.getRoleByName(roleName);
		Employee employee = employeeRepository.getEmployeeById(id);
		employee.setEmployeeFirstName(firstName);
		employee.setEmployeeLastName(lastName);
		employee.setDailyWage(dailyWage);
		employee.setRole(role);
		employee.setPassword(passwordEncoder.encode(password));

		employeeRepository.updateEmployee(employee);
	}

	@Transactional
	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteEmployee(getEmployeeById(id));
	}

	@Override
	public Employee authenticate(LoginEmployeeDTO employeeLogin) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				employeeLogin.getId(),
				employeeLogin.getPassword()
		));

		return employeeRepository.getEmployeeById(employeeLogin.getId());

	}


}
