package com.thomas.test.spring.web.controller;

import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.ContactService;
import com.thomas.test.spring.contactor.services.UserService;

@RequestMapping("/home")
@Controller
@SessionAttributes("user")
public class HomeController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.GET)
	public String home(
			@ModelAttribute("user") User user,
			@RequestParam(defaultValue = "0", required = false, value = "page") int page,
			@RequestParam(defaultValue = "10", required = false, value = "size") int size,
			@RequestParam(defaultValue = "DESC", required = false, value = "sort") Direction direction,
			@RequestParam(defaultValue = "firstName", required = false, value = "by") String by,
			Model uiModel) {

		Pageable pageRequest = new PageRequest(page, size, direction, by);

		uiModel.addAttribute("userName", user.getName());
		uiModel.addAttribute("contacts", contactService.findAllContactsForUser(user, pageRequest).getContent());

		return "home";
	}

	@RequestMapping(value = "/newContact")
	public String showCreateNewContact(
			@ModelAttribute("user") User user,
			Model uiModel) {

		Contact newContactBean = new Contact();

		uiModel.addAttribute("newContactBean", newContactBean);

		return "create";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createNewContact(
			@ModelAttribute("user") User user,
			@ModelAttribute("newContactBean") Contact newContact,
			Model uiModel) {

		// TODO: validation

		contactService.save(user, newContact);

		return "redirect:home";

	}
}
