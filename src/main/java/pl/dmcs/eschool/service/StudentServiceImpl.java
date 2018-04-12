package pl.dmcs.eschool.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmcs.eschool.dao.StudentDAO;
import pl.dmcs.eschool.domain.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;

	@Transactional
	public Student getStudent(int id) {
		return studentDAO.getStudent(id);
	}

	@Transactional
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
	}

	@Transactional
	public void removeStudent(int id) {
		studentDAO.removeStudent(id);
	}

	@Transactional
	public void editStudent(Student student) {
		studentDAO.editStudent(student);
	}

}
