package pl.dmcs.eschool.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;
import pl.dmcs.eschool.service.ClazzService;
import pl.dmcs.eschool.service.MarkService;
import pl.dmcs.eschool.service.SubjectService;
import pl.dmcs.eschool.service.TeacherService;

@RestController
public class EschoolRestController {

	@Autowired
	ClazzService clazzService;

	@Autowired
	MarkService markService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	TeacherService teacherService;

	@RequestMapping(value = "/getStudents/{clazzId}", method = RequestMethod.GET, produces = "application/json")
	public List<Student> getStudents(@PathVariable("clazzId") int clazzId) {
		return clazzService.getClazz(clazzId).getStudents();

	}

	@RequestMapping(value = "/getStudents/{clazzId}/{subjectId}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, List<Mark>> getListOfMarks(@PathVariable("clazzId") int clazzId,
			@PathVariable("subjectId") int subjectId) {

		Map<String, List<Mark>> map = new HashMap<>();
		List<Student> students = clazzService.getClazz(clazzId).getStudents();

		for (Student student : students) {
			map.put(student.getUser().getFirstname() + ' ' + student.getUser().getLastname(),
					markService.getMarks(student.getId(), subjectId));
		}

		return map;
	}

	@RequestMapping(value = "/getClazzes/{subjectId}", method = RequestMethod.GET, produces = "application/json")
	public List<Clazz> getClazzesWithoutTeacher(@PathVariable("subjectId") int subjectId) {

		List<Clazz> clazzes = clazzService.getClazzes();
		List<Teacher> teachers = subjectService.getSubject(subjectId).getTeachers();
		List<Integer> ids = new ArrayList<>();
		List<Clazz> clazzesWithoutTeacher = new ArrayList<>();

		for (int i = 0; i < teachers.size(); i++)
			for (Clazz clazz : subjectService.getSubject(subjectId).getTeachers().get(i).getClazzes()) {
				if (!ids.contains(clazz.getId())) {
					ids.add(clazz.getId());
				}
			}

		for (Clazz clazz : clazzes) {
			if (subjectService.getSubject(subjectId).getClazzes().contains(clazz) && !ids.contains(clazz.getId())) {
				clazzesWithoutTeacher.add(clazz);
			}

		}

		return clazzesWithoutTeacher;

	}

}
