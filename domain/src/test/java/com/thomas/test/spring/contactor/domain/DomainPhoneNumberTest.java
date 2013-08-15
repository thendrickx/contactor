package com.thomas.test.spring.contactor.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class DomainPhoneNumberTest extends AbstractDomainImplTest {

	@Test
	public void testCreatePhoneNumber() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		PhoneNumber phoneNumber = createNewPhoneNumber();

		em.persist(user);
		em.persist(contact);

		contact.addPhoneNumber(phoneNumber);

		em.persist(phoneNumber);

	}

	@Test
	public void testValidPhoneNumber() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		em.persist(user);
		em.persist(contact);

		Set<String> phoneNumbers = ImmutableSet.<String> of("061122334455", "+43 1 25 66 58 95",
				"0033 9 452 1 214", "06-12-25-11-99", "+1-256 354 125");

		int exceptions = 0;
		for (String phoneNumberString : phoneNumbers) {
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setPhoneNumber(phoneNumberString);
			contact.addPhoneNumber(phoneNumber);
			em.persist(phoneNumber);

			Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(phoneNumber);
			if (!violations.isEmpty()) {
				exceptions++;
				logger.debug(violations);
			}
		}

		assertEquals(0, exceptions);
	}

	@Test
	public void testInvalidPhoneNumber() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		em.persist(user);
		em.persist(contact);

		Set<String> phoneNumbers = ImmutableSet.<String> of("+555555 12", "jkhjhg kjh kj",
				"0033 9 452 1 214h", "12", "");

		int exceptions = 0;
		for (String phoneNumberString : phoneNumbers) {
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setPhoneNumber(phoneNumberString);
			contact.addPhoneNumber(phoneNumber);
			em.persist(phoneNumber);

			Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(phoneNumber);
			if (!violations.isEmpty()) {
				exceptions++;
			}

		}

		assertEquals(phoneNumbers.size(), exceptions);
	}
}
