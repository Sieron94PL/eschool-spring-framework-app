package pl.dmcs.eschool.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;
import pl.dmcs.eschool.service.ClazzService;
import pl.dmcs.eschool.service.MarkService;
import pl.dmcs.eschool.service.StudentService;
import pl.dmcs.eschool.service.SubjectService;
import pl.dmcs.eschool.service.TeacherService;
import pl.dmcs.eschool.service.UserService;

@Controller
@SessionAttributes
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	MarkService markService;

	@Autowired
	ClazzService clazzService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/student/marks")
	public String getMarks(Map<String, Object> map) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		map.put("student", userService.getUser(currentPrincipalName).getStudent());

		if (userService.getUser(currentPrincipalName).getStudent().getClazz() != null) {
			int clazzId = userService.getUser(currentPrincipalName).getStudent().getClazz().getId();
			int studentId = userService.getUser(currentPrincipalName).getStudent().getId();

			List<Mark> marks;
			Map<Teacher, List<Mark>> marksMap = new HashMap<>();

			for (Subject subject : clazzService.getSubjects(clazzId)) {

				marks = new ArrayList<>();

				for (Mark mark : markService.getMarks(studentId, subject.getId())) {
					marks.add(mark);
				}

				if (teacherService.findTeacherByClazzAndSubject(clazzId, subject.getId()) != null) {
					Teacher teacher = teacherService.findTeacherByClazzAndSubject(clazzId, subject.getId());
					marksMap.put(teacher, marks);
				} else {
					Teacher teacher = new Teacher();
					teacher.setSubject(subject);
					marksMap.put(teacher, marks);
				}
			}

			map.put("marks", marksMap);
		}
		return "studentMarks";

	}

	@RequestMapping(value = "/student/profile")
	public String getProfile(Map<String, Object> map) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		map.put("user", userService.getUser(currentPrincipalName));
		return "profile";
	}

	@RequestMapping(value = "student/downloadCertificate", method = RequestMethod.GET)
	public ModelAndView downloadCertificate() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		int studentId = userService.getUser(currentPrincipalName).getStudent().getId();
		int clazzId = userService.getUser(currentPrincipalName).getStudent().getClazz().getId();

		Student student = studentService.getStudent(studentId);
		List<Subject> subjects = clazzService.getSubjects(clazzId);
		Map<Subject, List<Mark>> certificate = new HashMap<>();

		List<Mark> marks;

		for (Subject subject : subjects) {
			marks = new ArrayList<>();

			for (Mark mark : markService.getMarks(student.getId(), subject.getId())) {
				marks.add(mark);
			}

			certificate.put(subject, marks);
		}

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("subjects", subjects);
		modelMap.put("student", student);
		modelMap.put("certificate", certificate);
		return new ModelAndView("pdfView", "modelMap", modelMap);

	}

}
