package pia.task.library_automation.restcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pia.task.library_automation.dto.EmployeeDTO;
import pia.task.library_automation.dto.LoginEmployeeDTO;
import pia.task.library_automation.dto.LoginResponse;
import pia.task.library_automation.entity.Employee;
import pia.task.library_automation.service.EmployeeService;
import pia.task.library_automation.service.JwtService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lib-auto/api/employee")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	private EmployeeService employeeService;
	private JwtService jwtService;

	@Autowired
	public EmployeeController(EmployeeService employeeService, JwtService jwtService)
	{
		this.employeeService = employeeService;
		this.jwtService = jwtService;
	}



	@GetMapping("/{id}")
	public EmployeeDTO getEmployee(@PathVariable int id)
	{
		try {
			System.out.println("getEmployee invoked");
			Employee employee = employeeService.getEmployeeById(id);
			return new EmployeeDTO(employee.getEmployeeId(), employee.getPassword(), employee.getEmployeeFirstName(),
					employee.getEmployeeLastName(), employee.getRegistrationDate(), employee.getDailyWage(),
					employee.getRole().getRole());
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/employees")
	public List<EmployeeDTO> getEmployees()
	{
		try {
			List<EmployeeDTO> responseList = new ArrayList<>();
			List<Employee> employees = employeeService.getEmployees();
			for(Employee employee: employees)
			{
				responseList.add(new EmployeeDTO(employee.getEmployeeId(), employee.getPassword(), employee.getEmployeeFirstName(),
						employee.getEmployeeLastName(), employee.getRegistrationDate(), employee.getDailyWage(),
						employee.getRole().getRole()));
			}
			return responseList;

		}catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/create")
	public String createEmployee(@RequestBody EmployeeDTO newEmployee)
	{
		try
		{
			employeeService.createEmployee(newEmployee.getId(), newEmployee.getPassword(), newEmployee.getFirstName(), newEmployee.getLastName(),
					newEmployee.getDailyWage(), newEmployee.getRole());
			return "operation is successful";
		}catch (Exception e)
		{
			//todo exception handling
			e.printStackTrace();
			return "operation failed";
		}
	}

	@PutMapping("/update")
	public String updateEmployee(@RequestBody EmployeeDTO updatedEmployee)
	{

		try {
			employeeService.updateEmployee(updatedEmployee.getId(), updatedEmployee.getPassword(), updatedEmployee.getFirstName(),
					updatedEmployee.getLastName(), updatedEmployee.getDailyWage(), updatedEmployee.getRole());
			return "operation is successful";
		} catch (Exception e) {
			e.printStackTrace();
			return "operation failed";
		}
	}


	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		try {
			employeeService.deleteEmployee(id);
			return "operation is successful";
		}catch (Exception e)
		{
			e.printStackTrace();
			return "operation is failed";
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginEmployeeDTO loginEmployeeDTO)
	{

		Employee authenticatedEMployee = employeeService.authenticate(loginEmployeeDTO);
		String jwtToken = jwtService.generateToken(authenticatedEMployee);

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getJwtExpiration());

		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}


}
