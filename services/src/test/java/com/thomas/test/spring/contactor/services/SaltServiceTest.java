package com.thomas.test.spring.contactor.services;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SaltServiceTest extends AbstractServiceTest {

	@Autowired
	SaltService saltService;

	@Test
	public void testSaltService() {
		String salt = saltService.getNewSaltAsAString();
		logger.debug(salt);
		assertFalse(salt.length() == 0);

	}
}
