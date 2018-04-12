package pl.dmcs.eschool.dao;

import java.util.List;

import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;

public interface StudentDAO {

	Student getStudent(int id);
	
	void addStudent(Student student);

	void removeStudent(int id);

	void editStudent(Student student);
	
	

}
