package pl.dmcs.eschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.dmcs.eschool.dao.UserRoleDAO;
import pl.dmcs.eschool.domain.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDAO userRoleDAO;

	@Transactional
	public void addRole(UserRole role) {
		userRoleDAO.addRole(role);
	}

}
