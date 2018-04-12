package pl.dmcs.eschool.service;

import java.util.List;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;

public interface TeacherService {

	Teacher getTeacher(int id);
	
	void addTeacher(Teacher teacher);

	void editTeacher(Teacher teacher);

	void removeTeacher(int id);
	
	List<Teacher> getClazzTeachers(int clazzId);
	
	Teacher findTeacherByClazzAndSubject(int clazzId, int subjectId);



}
