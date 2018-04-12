package pl.dmcs.eschool.service;

import java.util.List;

import pl.dmcs.eschool.domain.Subject;

public interface SubjectService {

	Subject getSubject(int id);

	void addSubject(Subject subject);

	void editSubject(Subject subject);

	void removeSubject(int id);

	List<Subject> getSubjects();
}
