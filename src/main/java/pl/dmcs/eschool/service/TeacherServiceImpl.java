package pl.dmcs.eschool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmcs.eschool.dao.TeacherDAO;
import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDAO teacherDAO;

	@Transactional
	public Teacher getTeacher(int id) {
		return teacherDAO.getTeacher(id);
	}

	@Transactional
	public void addTeacher(Teacher teacher) {
		teacherDAO.addTeacher(teacher);
	}

	@Transactional
	public void editTeacher(Teacher teacher) {
		teacherDAO.editTeacher(teacher);
	}

	@Transactional
	public void removeTeacher(int id) {
		teacherDAO.removeTeacher(id);
	}

	@Transactional
	public List<Teacher> getClazzTeachers(int clazzId) {
		return teacherDAO.getClazzTeachers(clazzId);
	}

	@Transactional
	public Teacher findTeacherByClazzAndSubject(int clazzId, int subjectId) {
		return teacherDAO.findTeacherByClazzAndSubject(clazzId, subjectId);
	}

}
