package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.domain.UserRole;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUser(String username) {
		return sessionFactory.getCurrentSession().get(User.class, username);
	}

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void removeUser(String username) {

		User user = sessionFactory.getCurrentSession().get(User.class, username);
		sessionFactory.getCurrentSession().delete(user);

	}

	@Override
	public void editUser(User user) {
		sessionFactory.getCurrentSession().update(user);

	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession().createQuery("from User where username = ?").setParameter(0, username)
				.list();

		if (users.size() > 0) {
			return users.get(0);

		} else {
			return null;
		}

	}

	@Override
	public List<User> getUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
		return users;
	}

}
