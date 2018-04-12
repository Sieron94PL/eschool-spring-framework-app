package pl.dmcs.eschool.dao;

import java.util.List;

import pl.dmcs.eschool.domain.User;

public interface UserDAO {

	void addUser(User user);

	void removeUser(String username);

	void editUser(User user);

	User findByUsername(String username);
	
	List<User> getUsers();
	
	User getUser(String username);
	
	
}
