package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.User;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	SessionFactory sessionFactory;

	public StudentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Student getStudent(int id) {
		return sessionFactory.getCurrentSession().get(Student.class, id);
	}

	@Override
	public void addStudent(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	@Override
	public void removeStudent(int id) {
		Student student = sessionFactory.getCurrentSession().get(Student.class, id);
		if (student != null) {
			sessionFactory.getCurrentSession().delete(student);
		}
	}

	@Override
	public void editStudent(Student student) {
		sessionFactory.getCurrentSession().update(student);
	}

}
