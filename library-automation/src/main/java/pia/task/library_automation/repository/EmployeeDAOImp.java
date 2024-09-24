package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImp implements EmployeeDAO{

	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public Employee getEmployeeByAuthentication(int id, String password) {
		String queryString = "select e from Employee e where e.employeeId = :id and e.password = :password";
		TypedQuery<Employee> query = entityManager.createQuery(queryString, Employee.class);
		query.setParameter("id", id);
		query.setParameter("password", password);

		return query.getSingleResult();
	}

	@Override
	public Employee getEmployeeById(int id) {
		String queryString = "select e from Employee e where e.employeeId = :id";
		TypedQuery<Employee> query = entityManager.createQuery(queryString, Employee.class);

		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public void updateEmployee(Employee employee) {
		entityManager.merge(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		entityManager.remove(employee);
	}

	@Override
	public void createEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	@Override
	public List<Employee> getEmployees() {

		String queryStr = "select e from Employee e";
		TypedQuery<Employee> query = entityManager.createQuery(queryStr, Employee.class);

		return query.getResultList();
	}


}
