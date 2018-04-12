package pl.dmcs.eschool.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;
import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.domain.UserRole;
import pl.dmcs.eschool.service.ClazzService;
import pl.dmcs.eschool.service.EschoolMailerService;
import pl.dmcs.eschool.service.MarkService;
import pl.dmcs.eschool.service.MyUserDetailsService;
import pl.dmcs.eschool.service.StudentService;
import pl.dmcs.eschool.service.SubjectService;
import pl.dmcs.eschool.service.TeacherService;
import pl.dmcs.eschool.service.UserRoleService;
import pl.dmcs.eschool.service.UserService;
import validator.RegisterValidator;

@Controller
public class AdminController {

	@Autowired
	ClazzService clazzService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	UserService userService;

	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	MarkService markService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	EschoolRestController eschoolRestController;

	@Autowired
	EschoolMailerService eschoolMailerService;

	RegisterValidator registerValidator = new RegisterValidator();

	@RequestMapping(value = "admin/users")
	public String users(Map<String, Object> map, HttpServletRequest request) throws ServletRequestBindingException {

		String username = ServletRequestUtils.getStringParameter(request, "username");

		if (username != null) {

			map.put("edit", true);

			if (userService.getUser(username).getUserRole().size() != 0) {
				Set<UserRole> set = userService.getUser(username).getUserRole();
				List<UserRole> userRoles = new ArrayList<UserRole>(set);

				if (userRoles.get(0).getRole().equals("ROLE_ADMIN")) {
					map.put("admin", true);
				}
			}

			map.put("user", userService.getUser(username));

			if (userService.getUser(username).getStudent() != null) {
				if (userService.getUser(username).getStudent().getClazz() != null) {
					map.put("clazz", userService.getUser(username).getStudent().getClazz());
				}
			}
			if (userService.getUser(username).getTeacher() != null) {
				if (userService.getUser(username).getTeacher().getSubject() != null) {
					map.put("subject", userService.getUser(username).getTeacher().getSubject());
					map.put("teacherClazzes", userService.getUser(username).getTeacher().getClazzes());
					map.put("clazzesWithoutTeacher", eschoolRestController
							.getClazzesWithoutTeacher(userService.getUser(username).getTeacher().getSubject().getId()));
				}
			}

		}

		map.put("users", userService.getUsers());
		map.put("clazzes", clazzService.getClazzes());
		map.put("subjects", subjectService.getSubjects());
		return "users";
	}

	@RequestMapping(value = "admin/editUser", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user, HttpServletRequest request, BindingResult result) {

		String role = request.getParameter("roles");
		String studentClazz = request.getParameter("studentClazz");
		String[] teacherClazzes = request.getParameterValues("teacherClazzes");
		String subject = request.getParameter("subjects");

		UserRole userRole = new UserRole();
		List<Clazz> clazzes = new ArrayList<Clazz>();

		if (role != null) {
			if (role.equals("student_id")) {

				Student student = new Student();
				student.setUser(user);

				if (Integer.parseInt(studentClazz) != 0)
					student.setClazz(clazzService.getClazz(Integer.parseInt(studentClazz)));

				studentService.addStudent(student);
				user.setStudent(student);
				userRole.setRole("ROLE_STUDENT");

			} else if (role.equals("teacher_id")) {

				Teacher teacher = new Teacher();
				teacher.setUser(user);

				if (Integer.parseInt(subject) != 0) {
					teacher.setSubject(subjectService.getSubject(Integer.parseInt(subject)));
				}

				if (teacherClazzes != null) {
					for (String clazz : teacherClazzes) {
						clazzes.add(clazzService.getClazz(Integer.parseInt(clazz)));
					}
					teacher.setClazzes(clazzes);
				}

				teacherService.addTeacher(teacher);
				user.setTeacher(teacher);
				userRole.setRole("ROLE_TEACHER");

			} else {
				userRole.setRole("ROLE_ADMIN");
			}

			userRole.setUser(user);
			userRoleService.addRole(userRole);
		}

		if (userService.getUser(user.getUsername()).getUserRole().size() != 0 && role == null) {

			Set<UserRole> set = userService.getUser(user.getUsername()).getUserRole();
			List<UserRole> userRoles = new ArrayList<UserRole>(set);
			Teacher teacher = userService.getUser(user.getUsername()).getTeacher();

			if (userRoles.get(0).getRole().equals("ROLE_TEACHER")) {

				if (subject != null) {
					if (Integer.parseInt(subject) != 0) {
						teacher.setSubject(subjectService.getSubject(Integer.parseInt(subject)));
					}
				}

				if (teacherClazzes != null) {
					for (String clazz : teacherClazzes) {
						clazzes.add(clazzService.getClazz(Integer.parseInt(clazz)));
					}
					teacher.setClazzes(clazzes);
				} else {
					teacher.setClazzes(new ArrayList<Clazz>());
				}

				teacherService.editTeacher(teacher);
				user.setTeacher(teacher);
			}

			if (userRoles.get(0).getRole().equals("ROLE_STUDENT")) {

				Student student = userService.getUser(user.getUsername()).getStudent();

				if (studentClazz != null) {
					student.setClazz(clazzService.getClazz(Integer.parseInt(studentClazz)));
				}

				studentService.editStudent(student);
				user.setStudent(student);
			}
		}

		registerValidator.validate(user, result);
		if (result.getErrorCount() == 0) {
			userService.editUser(user);
		}

		return "redirect:/admin/users";
	}

	@RequestMapping("admin/deleteUser/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		userService.removeUser(username);
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "admin/subjects")
	public String subjects(Model model, Map<String, Object> map) {

		model.addAttribute("subject", new Subject());
		map.put("subjects", subjectService.getSubjects());

		model.addAttribute("clazz", new Clazz());
		map.put("clazzes", clazzService.getClazzes());

		return "subjects";

	}

	@RequestMapping(value = "admin/clazzes")
	public String clazzes(Model model, Map<String, Object> map, HttpServletRequest request) {

		int clazzId = ServletRequestUtils.getIntParameter(request, "clazzId", -1);

		if (clazzId > 0) {
			map.put("clazzSubjects", clazzService.getSubjects(clazzId));
			map.put("clazz", clazzService.getClazz(clazzId));
			map.put("availableSubjects", clazzService.getAvailableSubjects(clazzId));
			System.out.println(clazzService.getAvailableSubjects(clazzId));
		}

		else {
			model.addAttribute("clazz", new Clazz());
		}

		map.put("clazzes", clazzService.getClazzes());

		return "clazzes";

	}

	@RequestMapping(value = "admin/addSubject", method = RequestMethod.POST)
	public String addSubject(@ModelAttribute("subject") Subject subject, BindingResult result) {

		if (!subjectService.getSubjects().contains(subject) && subject.getName() != "") {
			subjectService.addSubject(subject);
		}

		return "redirect:subjects.html";
	}

	@RequestMapping(value = "admin/addClazz", method = RequestMethod.POST)
	public String addClass(@ModelAttribute("clazz") Clazz clazz) {
		if (!clazzService.getClazzes().contains(clazz) && clazz.getName() != "") {
			clazzService.addClazz(clazz);
		}
		return "redirect:clazzes.html";
	}

	@RequestMapping("admin/addSubjectToClazz/{subjectId}/{clazzId}")
	public String addSubjectToClazz(@PathVariable("subjectId") Integer subjectId,
			@PathVariable("clazzId") Integer clazzId) {

		Subject subject = subjectService.getSubject(subjectId);
		List<Clazz> clazzes = subject.getClazzes();
		clazzes.add(clazzService.getClazz(clazzId));
		subject.setClazzes(clazzes);
		subjectService.editSubject(subject);

		return "redirect:/admin/clazzes.html?clazzId=" + clazzId;
	}

	@RequestMapping("admin/removeSubjectFromClazz/{subjectId}/{clazzId}")
	public String removeSubjectFromClazz(@PathVariable("subjectId") Integer subjectId,
			@PathVariable("clazzId") Integer clazzId) {

		Subject subject = subjectService.getSubject(subjectId);
		List<Clazz> clazzes = subject.getClazzes();
		clazzes.remove(clazzService.getClazz(clazzId));
		subject.setClazzes(clazzes);
		subjectService.editSubject(subject);

		return "redirect:/admin/clazzes.html?clazzId=" + clazzId;
	}

}
