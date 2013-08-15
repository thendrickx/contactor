package com.thomas.test.spring.contactor.domain;

import org.junit.Test;

public class DomainLabelTest extends AbstractDomainImplTest {

	@Test
	public void testContactLabel() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		Label label = createNewLabel("FRIEND");

		contact.addLabel(label);
		user.addLabel(label);

		em.persist(user);
		em.persist(contact);
		em.persist(label);
	}

	@Test
	public void testEmailAddressLabel() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		EmailAddress emailAddress = createNewEmailAddress();

		contact.addEmailAddress(emailAddress);

		Label label = createNewLabel("WORK");

		emailAddress.addLabel(label);
		user.addLabel(label);

		em.persist(user);
		em.persist(contact);
		em.persist(emailAddress);
		em.persist(label);
	}

	@Test
	public void testAddressLabel() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		Address address = createNewAddress();

		contact.addAddress(address);

		Label label = createNewLabel("BOB");

		address.addLabel(label);
		user.addLabel(label);

		em.persist(user);
		em.persist(contact);
		em.persist(address);
		em.persist(label);

	}

	@Test
	public void testPhoneNumberLabel() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		PhoneNumber phoneNumber = createNewPhoneNumber();

		contact.addPhoneNumber(phoneNumber);

		Label label = createNewLabel("TEST");

		phoneNumber.addLabel(label);
		user.addLabel(label);

		em.persist(user);
		em.persist(contact);
		em.persist(phoneNumber);
		em.persist(label);

	}
}
