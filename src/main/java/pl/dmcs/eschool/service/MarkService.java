package pl.dmcs.eschool.service;

import java.util.List;

import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Subject;

public interface MarkService {
	
	void addMark(Mark mark);

	List<Mark> getMarks(int studentId, int subjectId);
	
	List<Mark> getSubjects(int studentId);	
	
}
