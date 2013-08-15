package com.thomas.test.spring.web.services;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface ValidationService {

	public <T> Set<ConstraintViolation<T>> partialValidation(
			T bean,
			String... properties);
}
