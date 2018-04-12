package pl.dmcs.eschool.dao;

import java.util.List;

import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Subject;

public interface MarkDAO {
	
	void addMark(Mark mark);
	
	List<Mark> getMarks(int studentId, int subjectId);
	
	List<Mark> getSubjects(int studentId);

}
