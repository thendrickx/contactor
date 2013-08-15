package com.thomas.test.spring.web.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.UserService;
import com.thomas.test.spring.web.services.LoginService;
import com.thomas.test.spring.web.services.ValidationService;

@RequestMapping("/login")
@Controller
public class UserLoginController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private HttpSession session;

	@Autowired
	private ValidationService validationService;

	@RequestMapping(method = RequestMethod.GET)
	public String showLoginScreen(
			Model uiModel) {

		// If the user is in the session, he should be logged in,
		// redirect to home.
		if (session.getAttribute("user") != null) {
			return "redirect:/home";
		}

		// If the model contains no user bean, create a new one
		// and attach it to the session.
		if (!uiModel.containsAttribute("user")) {
			User user = new User();
			uiModel.addAttribute("user", user);
		}

		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(
			@ModelAttribute("user") User userBean,
			Model uiModel) {

		Set<ConstraintViolation<User>> violations = validationService.<User> partialValidation(userBean, "emailAddress", "password");
		if (!violations.isEmpty()) {

			StringBuilder sb = new StringBuilder();

			for (ConstraintViolation<User> violation : violations) {
				sb.append(violation.getPropertyPath() + ": ");
				sb.append(violation.getMessage() + " <br/> ");
			}

			uiModel.addAttribute("loginMessage", new Message(sb.toString()));
			return showLoginScreen(uiModel);
		}

		try {

			User loggedInUser = loginService.logUserIntoApplication(userBean.getEmailAddress(), userBean.getPassword());
			session.setAttribute("user", loggedInUser);

			return "redirect:/home";
		} catch (BadCredentialsException e) {
			uiModel.addAttribute("loginMessage", new Message(e.getMessage()));
			return showLoginScreen(uiModel);

		}
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String createNewUser(
			@ModelAttribute("user") User user,
			Model uiModel) {

		Set<ConstraintViolation<User>> violations = validationService.<User> partialValidation(user, "name", "emailAddress", "password");
		if (!violations.isEmpty()) {

			StringBuilder sb = new StringBuilder();

			for (ConstraintViolation<User> violation : violations) {
				sb.append(violation.getPropertyPath() + ": ");
				sb.append(violation.getMessage() + " <br/> ");
			}

			uiModel.addAttribute("createUserMessage", new Message(sb.toString()));
			return showLoginScreen(uiModel);

		} else {

			try {
				String plainTextPassword = user.getPassword();
				userService.save(user);

				User loggedInUser = loginService.logUserIntoApplication(user.getEmailAddress(), plainTextPassword);
				session.setAttribute("user", loggedInUser);

				return "redirect:/home";

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				uiModel.addAttribute("createUserMessage", new Message(e.getMessage()));
				return showLoginScreen(uiModel);
			}

		}
	}
}
