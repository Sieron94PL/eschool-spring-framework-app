package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

public class TeacherDAOImpl implements TeacherDAO {

	@Autowired
	SessionFactory sessionFactory;

	public TeacherDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Teacher getTeacher(int id) {
		return sessionFactory.getCurrentSession().get(Teacher.class, id);
	}

	@Override
	public void addTeacher(Teacher teacher) {
		sessionFactory.getCurrentSession().save(teacher);
	}

	@Override
	public void editTeacher(Teacher teacher) {
		sessionFactory.getCurrentSession().update(teacher);
	}

	@Override
	public void removeTeacher(int id) {
		Teacher teacher = sessionFactory.getCurrentSession().get(Teacher.class, id);
		if (teacher != null) {
			sessionFactory.getCurrentSession().delete(teacher);
		}
	}

	@Override
	public List<Teacher> getClazzTeachers(int clazzId) {
		@SuppressWarnings("unchecked")
		List<Object[]> result = sessionFactory.getCurrentSession()
				.createQuery("from Teacher t join t.clazzes tc where tc.id = ?").setParameter(0, clazzId).list();

		List<Teacher> teachers = new ArrayList<>();

		for (Object[] o : result) {
			teachers.add((Teacher) o[0]);
		}

		return teachers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Teacher findTeacherByClazzAndSubject(int clazzId, int subjectId) {
		List<Object[]> result = sessionFactory.getCurrentSession()
				.createQuery("from Teacher t join t.clazzes tc where tc.id = ? and t.subject.id = ?")
				.setParameter(0, clazzId).setParameter(1, subjectId).list();
		if (result.size() == 0) {
			return null;
		} else {
			return (Teacher) result.get(0)[0];
		}
	}
}
