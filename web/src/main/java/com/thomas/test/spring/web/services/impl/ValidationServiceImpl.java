package com.thomas.test.spring.web.services.impl;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomas.test.spring.web.services.ValidationService;

@Service("validationService")
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	private Validator validator;

	@Override
	public <T> Set<ConstraintViolation<T>> partialValidation(
			T bean,
			String... properties) {

		Set<ConstraintViolation<T>> result = new HashSet<ConstraintViolation<T>>();

		for (String property : properties) {
			result.addAll(validator.validateProperty(bean, property));
		}

		return result;
	}
}
