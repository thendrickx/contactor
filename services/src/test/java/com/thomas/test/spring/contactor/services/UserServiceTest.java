package com.thomas.test.spring.contactor.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.annotations.DataSets;

public class UserServiceTest extends AbstractServiceTest {

	@Autowired
	UserService userService;

	@DataSets(setUpDataSet = "/com/thomas/test/spring/contactor/services/sampleData.xls")
	@Test
	@Ignore
	public void testFindAll() throws Exception {
		Collection<User> users = userService.findAll();

		assertNotNull(users);
		assertFalse(users.isEmpty());

		logger.debug(users);

	}

	@DataSets(setUpDataSet = "/com/thomas/test/spring/contactor/services/sampleData.xls")
	@Test
	public void testPersistence() {
		User user = userService.findAll().iterator().next();
		logger.debug(user.getContacts());

		for (Contact contact : user.getContacts()) {
			logger.debug(contact + " - " + contact.getEmailAddresses());

		}
	}

	@Test
	@Ignore
	public void testCreateUser() {
		User user = userService.createUser("zorro@tornado.com", "Z0rr0");

		logger.debug(user.getUserId());
	}
}
