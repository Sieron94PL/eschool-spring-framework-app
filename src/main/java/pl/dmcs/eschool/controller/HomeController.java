package pl.dmcs.eschool.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.service.ClazzService;
import pl.dmcs.eschool.service.MarkService;
import pl.dmcs.eschool.service.StudentService;
import pl.dmcs.eschool.service.UserService;
import validator.RegisterValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	static {
		System.setProperty("java.net.useSystemProxies", "true");
	}

	@Autowired
	private ReCaptcha reCaptcha = null;

	@Autowired
	UserService userService;

	@Autowired
	StudentService studentService;

	@Autowired
	ClazzService clazzService;

	@Autowired
	MarkService markService;

	RegisterValidator registerValidator = new RegisterValidator();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		if (currentPrincipalName.equals("anonymousUser")) {
			return "login";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		if (currentPrincipalName.equals("anonymousUser")) {
			model.setViewName("login");
		} else {
			model.setViewName("home");
		}
		return model;
	}

	@RequestMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	
	@RequestMapping(value="/403")
	public String accessDenied(){
		return "403";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, BindingResult result,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field") String responseField, ServletRequest servletRequest,
			Map<String, Object> map) {

		String remoteAddress = servletRequest.getRemoteAddr();
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);

		registerValidator.validate(user, result);
		if (result.getErrorCount() == 0 && reCaptchaResponse.isValid()
				&& userService.getUser(user.getUsername()) == null) {
			userService.addUser(user);
			return "redirect:login.html";
		} else if (!reCaptchaResponse.isValid()) {
			map.put("invalidCaptcha", "Invalid captcha");
			return "registration";
		} else if (userService.getUser(user.getUsername()) != null) {
			map.put("usernameIsBusy", "Username is busy");
			return "registration";
		} else {
			return "registration";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
