package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.domain.UserRole;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addRole(UserRole role) {
		sessionFactory.getCurrentSession().save(role);
	}

}
