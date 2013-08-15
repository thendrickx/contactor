package com.thomas.test.spring.contactor.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class DomainContactTest extends AbstractDomainImplTest {

	@Test
	public void testCreateContacts() {
		User user = createNewUser();
		em.persist(user);
		Contact contact = createNewContact();
		user.addContact(contact);
		em.persist(contact);

		List<User> users = getAllInstances(User.class);
		for (User currentUser : users) {
			logger.info(currentUser + " - contacts: " + currentUser.getContacts());
		}

		List<Contact> contacts = getAllInstances(Contact.class);
		for (Contact currentContact : contacts) {
			logger.info(currentContact + " - user: " + currentContact.getUser());
		}

		assertEquals(1L, user.getContacts().size());
		assertEquals(contact, user.getContacts().iterator().next());
		assertEquals(user, contact.getUser());
		assertEquals(contact.hashCode(), user.getContacts().iterator().next().hashCode());

	}
}
