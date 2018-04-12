package pl.dmcs.eschool.service;

import java.util.List;

import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.domain.UserRole;

public interface UserService {

	User getUser(String username);

	void addUser(User user);

	void removeUser(String username);

	void editUser(User user);

	String hashPassword(String password);

	User findByUsername(String username);

	List<User> getUsers();

}
