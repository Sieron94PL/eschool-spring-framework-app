package pl.dmcs.eschool.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;
import pl.dmcs.eschool.service.ClazzService;
import pl.dmcs.eschool.service.EschoolMailerService;
import pl.dmcs.eschool.service.MarkService;
import pl.dmcs.eschool.service.StudentService;
import pl.dmcs.eschool.service.TeacherService;
import pl.dmcs.eschool.service.UserService;

@Controller
public class TeacherController {

	@Autowired
	UserService userService;

	@Autowired
	MarkService markService;

	@Autowired
	StudentService studentService;

	@Autowired
	EschoolMailerService eschoolMailerService;

	@RequestMapping(value = "/teacher/marks")
	public String addMark(Model model, Map<String, Object> map) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Map<Student, List<Mark>> markList = new HashMap<>();
		List<Mark> marks = new ArrayList<Mark>();
		if (userService.getUser(currentPrincipalName).getTeacher().getClazzes().size() != 0) {
			for (Student student : userService.getUser(currentPrincipalName).getTeacher().getClazzes().get(0)
					.getStudents()) {
				marks = markService.getMarks(student.getId(),
						userService.getUser(currentPrincipalName).getTeacher().getSubject().getId());
				markList.put(student, marks);
			}
			map.put("students",
					userService.getUser(currentPrincipalName).getTeacher().getClazzes().get(0).getStudents());
		}
		model.addAttribute("mark", new Mark());
		map.put("clazzes", userService.getUser(currentPrincipalName).getTeacher().getClazzes());
		map.put("subject", userService.getUser(currentPrincipalName).getTeacher().getSubject());
		map.put("markList", markList);
		map.put("teacher", userService.getUser(currentPrincipalName).getTeacher());

		return "teacherMarks";
	}

	@RequestMapping(value = "/teacher/addMark", method = RequestMethod.POST)
	public String addMark(@ModelAttribute("mark") Mark mark, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Teacher teacher = userService.getUser(currentPrincipalName).getTeacher();
		Student student = studentService.getStudent(Integer.parseInt(request.getParameter("students")));
		mark.setStudent(student);
		markService.addMark(mark);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		String url = "http://localhost:8080/eschool/";

		String format = "Witaj <strong>%s</strong>,<br/><br/>Dodano ocenê <strong>%.1f</strong> z przedmiotu <strong>%s</strong>.<br/>Aby zobaczyæ listê Twoich ocen zaloguj siê do na portal <a href=%s>Eschool</a>."
				+ " <br/><br/>Wiadomoœæ ta zosta³a wys³ana automatycznie | dok³adna data i czas: %s";

		String body = String.format(format, student.getUser().getUsername(), mark.getMark(),
				teacher.getSubject().getName(), url, df.format(date.getTime()));

		String to = student.getUser().getEmail();

		String subject = "Eschool - dodano now¹ ocenê";

		eschoolMailerService.sendMail(to, subject, body);

		return "redirect:/teacher/marks";
	}

	@RequestMapping(value = "/teacher/profile")
	public String getProfile(Map<String, Object> map) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		map.put("user", userService.getUser(currentPrincipalName));
		return "profile";
	}

}
