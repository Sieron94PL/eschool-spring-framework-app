package pl.dmcs.eschool.dao;

import java.util.List;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;
import pl.dmcs.eschool.domain.User;

public interface TeacherDAO {

	Teacher getTeacher(int id);

	void addTeacher(Teacher teacher);

	void editTeacher(Teacher teacher);

	void removeTeacher(int id);

	List<Teacher> getClazzTeachers(int clazzId);
	
	Teacher findTeacherByClazzAndSubject(int clazzId, int subjectId);

}
