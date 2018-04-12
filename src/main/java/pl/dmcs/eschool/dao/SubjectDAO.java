package pl.dmcs.eschool.dao;

import java.util.List;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Subject;

public interface SubjectDAO {

	void addSubject(Subject subject);

	void editSubject(Subject subject);

	void removeSubject(int id);

	Subject getSubject(int id);

	List<Subject> getSubjects();	

}
