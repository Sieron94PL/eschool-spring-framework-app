package pl.dmcs.eschool.service;

import java.util.List;

import pl.dmcs.eschool.domain.Student;

public interface StudentService {

	Student getStudent(int id);

	void addStudent(Student student);

	void removeStudent(int id);

	void editStudent(Student student);

}
