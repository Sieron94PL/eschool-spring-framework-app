package pl.dmcs.eschool.dao;

import java.util.List;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

public interface ClazzDAO {

	Clazz getClazz(int id);

	void addClazz(Clazz clazz);

	void editClazz(Clazz clazz);

	void removeClazz(int id);

	List<Clazz> getClazzes();
	
	List<Subject> getSubjects(int clazzId);
	
	List<Subject> getAvailableSubjects(int clazzId);
	

}
