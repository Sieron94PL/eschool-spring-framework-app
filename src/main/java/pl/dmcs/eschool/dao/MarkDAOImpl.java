package pl.dmcs.eschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;

@Repository
public class MarkDAOImpl implements MarkDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addMark(Mark mark) {
		sessionFactory.getCurrentSession().save(mark);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mark> getMarks(int studentId, int subjectId) {
		List<Mark> marks = new ArrayList<>();
		marks = sessionFactory.getCurrentSession()
				.createQuery("FROM Mark WHERE student_id = " + studentId + " AND subject_id = " + subjectId).list();

		return marks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mark> getSubjects(int studentId) {
		List<Mark> subjects = new ArrayList<>();
		subjects = sessionFactory.getCurrentSession()
				.createQuery("FROM Mark WHERE student_id = " + studentId + " GROUP BY subject_id").list();

		return subjects;

	}

}
