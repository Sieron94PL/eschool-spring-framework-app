package pl.dmcs.eschool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmcs.eschool.dao.ClazzDAO;
import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

@Service
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	ClazzDAO clazzDAO;

	@Transactional
	public Clazz getClazz(int id) {
		return clazzDAO.getClazz(id);
	}

	@Transactional
	public void addClazz(Clazz clazz) {
		clazzDAO.addClazz(clazz);
	}

	@Transactional
	public void editClazz(Clazz clazz) {
		clazzDAO.editClazz(clazz);
	}

	@Transactional
	public void removeClazz(int id) {
		clazzDAO.removeClazz(id);
	}

	@Transactional
	public List<Clazz> getClazzes() {
		return clazzDAO.getClazzes();
	}

	@Transactional
	public List<Subject> getSubjects(int clazzId) {
		return clazzDAO.getSubjects(clazzId);
	}

	@Transactional
	public List<Subject> getAvailableSubjects(int clazzId) {
		return clazzDAO.getAvailableSubjects(clazzId);
	}

}
