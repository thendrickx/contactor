package com.thomas.test.spring.contactor.domain;

import org.junit.Test;

public class DomainAddressTest extends AbstractDomainImplTest {

	@Test
	public void testCreateAddress() {
		User user = createNewUser();
		Contact contact = createNewContact();

		user.addContact(contact);

		Address address = createNewAddress();
		contact.addAddress(address);

		em.persist(user);
		em.persist(contact);
		em.persist(address);
	}
}
