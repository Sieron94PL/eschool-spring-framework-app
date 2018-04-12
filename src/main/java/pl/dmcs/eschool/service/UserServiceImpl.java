package pl.dmcs.eschool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.dmcs.eschool.dao.UserDAO;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.domain.UserRole;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

	@Transactional
	public void addUser(User user) {
		user.setPassword(hashPassword(user.getPassword()));
		userDAO.addUser(user);

	}

	@Transactional
	public void removeUser(String username) {
		userDAO.removeUser(username);

	}

	@Transactional
	public void editUser(User user) {
		userDAO.editUser(user);
	}

	@Transactional
	public String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Transactional
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();

	}

}
