package pl.dmcs.eschool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmcs.eschool.dao.SubjectDAO;
import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDAO subjectDAO;

	@Transactional
	public void addSubject(Subject subject) {
		subjectDAO.addSubject(subject);
	}

	@Transactional
	public void editSubject(Subject subject) {
		subjectDAO.editSubject(subject);
	}

	@Transactional
	public void removeSubject(int id) {
		subjectDAO.removeSubject(id);
	}

	@Transactional
	public Subject getSubject(int id) {
		return subjectDAO.getSubject(id);
	}

	@Transactional
	public List<Subject> getSubjects() {
		return subjectDAO.getSubjects();		
	}

}
