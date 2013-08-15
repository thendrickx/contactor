package com.thomas.test.spring.contactor.domain;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

public class DomainEmailAddressTest extends AbstractDomainImplTest {

	@Test
	public void testCreateEmailAddress() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		EmailAddress emailAddress = createNewEmailAddress();
		contact.addEmailAddress(emailAddress);

		em.persist(user);
		em.persist(contact);
		em.persist(emailAddress);

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator
				.validate(emailAddress);

		assertTrue(constraintViolations.isEmpty());

	}

	@Test
	public void testInvalidEmailAddress() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		EmailAddress emailAddress = createNewEmailAddress();
		emailAddress.setEmailAddress("q");
		contact.addEmailAddress(emailAddress);

		em.persist(user);
		em.persist(contact);
		em.persist(emailAddress);

		Set<ConstraintViolation<EmailAddress>> constraintViolations = validator
				.validate(emailAddress);

		assertTrue(!constraintViolations.isEmpty());
		logger.trace(constraintViolations);

	}
}
