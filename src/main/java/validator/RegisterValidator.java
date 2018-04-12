package validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.service.UserService;

import org.apache.commons.validator.routines.EmailValidator;

public class RegisterValidator implements Validator {

	private static String nameRegex = "[a-zA-Z]*";
	private static String digitRegex = ".*\\p{Digit}.*";
	private int minPasswordLength;
	private int maxPasswordLength;

	EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	UserService userService;

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(User user, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "error.field.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.field.required");
		ValidationUtils.rejectIfEmpty(errors, "firstname", "error.field.required");
		ValidationUtils.rejectIfEmpty(errors, "lastname", "error.field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.field.required");
		ValidationUtils.rejectIfEmpty(errors, "telephone", "error.field.required");

		if (errors.getErrorCount() == 0) {
			if (StringUtils.hasText(user.getEmail()) && emailValidator.isValid(user.getEmail()) == false) {
				errors.rejectValue("email", "error.email.invaild");
			}
		}

	}

	public void setMinPasswordLength(int minPasswordLength) {
		this.minPasswordLength = minPasswordLength;
	}

	public void setMaxPasswordLength(int maxPasswordLength) {
		this.maxPasswordLength = maxPasswordLength;
	}
}
