package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

@Repository
public class ClazzDAOImpl implements ClazzDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ClazzDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Clazz getClazz(int id) {
		return (Clazz) sessionFactory.getCurrentSession().get(Clazz.class, id);
	}

	@Override
	public void addClazz(Clazz clazz) {
		sessionFactory.getCurrentSession().save(clazz);
	}

	@Override
	public void editClazz(Clazz clazz) {
		sessionFactory.getCurrentSession().update(clazz);

	}

	@Override
	public void removeClazz(int id) {
		Clazz clazz = (Clazz) sessionFactory.getCurrentSession().load(Clazz.class, id);
		if (clazz != null) {
			sessionFactory.getCurrentSession().delete(clazz);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Clazz> getClazzes() {
		List<Clazz> clazzes = new ArrayList<Clazz>();
		clazzes = sessionFactory.getCurrentSession().createQuery("from Clazz").list();
		return clazzes;
	}

	@Override
	public List<Subject> getSubjects(int clazzId) {
		@SuppressWarnings("unchecked")
		List<Object[]> result = sessionFactory.getCurrentSession()
				.createQuery("from Subject s join s.clazzes sc where sc.id = ?").setParameter(0, clazzId).list();

		List<Subject> subjects = new ArrayList<>();

		for (Object[] o : result) {
			subjects.add((Subject) o[0]);
		}

		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAvailableSubjects(int clazzId) {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = sessionFactory.getCurrentSession().createQuery("from Subject").list();

		List<Object[]> result = sessionFactory.getCurrentSession()
				.createQuery("from Subject s join s.clazzes sc where sc.id = ?").setParameter(0, clazzId).list();

		List<Subject> clazzSubjects = new ArrayList<>();

		for (Object[] o : result) {
			clazzSubjects.add((Subject) o[0]);
		}

		if (clazzSubjects.size() != 0) {
			subjects.removeAll(clazzSubjects);
		}

		return subjects;
	}

}
