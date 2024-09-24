package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.Role;

@Repository
public class RoleDAOImp implements RoleDAO{

	private EntityManager entityManager;

	@Autowired
	public RoleDAOImp(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}



	@Override
	public Role getRoleByName(String name) {

		String queryStr = "select r from Role r where r.role = :role";
		TypedQuery<Role> query = entityManager.createQuery(queryStr, Role.class);
		query.setParameter("role", name);

		return query.getSingleResult();
	}
}
