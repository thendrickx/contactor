package com.thomas.test.spring.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.UserService;
import com.thomas.test.spring.web.services.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationProvider daoAuthenticationProvider;

	@Override
	public User logUserIntoApplication(
			String emailAddress,
			String password) throws BadCredentialsException {
		Authentication request = new UsernamePasswordAuthenticationToken(emailAddress, password);

		Authentication result = daoAuthenticationProvider.authenticate(request);
		SecurityContextHolder.getContext().setAuthentication(result);

		User user = userService.findByEmailAddress(emailAddress);

		return user;
	}
}
