package com.thomas.test.spring.web.services;

import org.springframework.security.authentication.BadCredentialsException;

import com.thomas.test.spring.contactor.domain.User;

public interface LoginService {

	public User logUserIntoApplication(
			String emailAddress,
			String password) throws BadCredentialsException;
}
