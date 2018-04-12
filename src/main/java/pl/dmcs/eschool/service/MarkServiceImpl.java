package pl.dmcs.eschool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmcs.eschool.dao.MarkDAO;
import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Subject;

@Service
public class MarkServiceImpl implements MarkService {

	@Autowired
	MarkDAO markDAO;

	@Transactional
	public void addMark(Mark mark) {
		markDAO.addMark(mark);

	}

	@Transactional
	public List<Mark> getMarks(int studentId, int subjectId) {
		return markDAO.getMarks(studentId, subjectId);
	}

	@Transactional
	public List<Mark> getSubjects(int studentId) {
		return markDAO.getSubjects(studentId);
	}

}
