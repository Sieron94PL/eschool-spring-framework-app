package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

public class SubjectDAOImpl implements SubjectDAO {

	@Autowired
	SessionFactory sessionFactory;

	public SubjectDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Subject getSubject(int id) {
		return sessionFactory.getCurrentSession().get(Subject.class, id);
	}

	@Override
	public void addSubject(Subject subject) {
		sessionFactory.getCurrentSession().save(subject);
	}

	@Override
	public void editSubject(Subject subject) {
		sessionFactory.getCurrentSession().update(subject);

	}

	@Override
	public void removeSubject(int id) {
		Subject subject = sessionFactory.getCurrentSession().get(Subject.class, id);
		if (subject != null) {
			sessionFactory.getCurrentSession().delete(subject);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = sessionFactory.getCurrentSession().createQuery("from Subject").list();
		return subjects;
	}

}
