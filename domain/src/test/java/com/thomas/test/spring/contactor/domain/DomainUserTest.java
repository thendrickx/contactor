package com.thomas.test.spring.contactor.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class DomainUserTest extends AbstractDomainImplTest {

	@Test
	public void testCreateUser() {

		User user = createNewUser();

		em.persist(user);

		List<User> allUsers = getAllInstances(User.class);
		assertEquals(1L, allUsers.size());
		assertEquals(user, allUsers.get(0));
		logger.info("TEST CREATE: " + allUsers);

	}

}
