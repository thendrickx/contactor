package com.thomas.test.spring.contactor.services;

import java.util.Set;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.User;

public interface ContactMergingService {

	/**
	 * Finds all contacts being potential duplicates (same email address, same
	 * address, same phone number, same name etc.)
	 * 
	 * @return A Set of potentially duplicated contact sets.
	 */
	public Set<Set<Contact>> findPotentialDuplicatesForUser(User user);

	/**
	 * Merges duplicated contacts into one. Duplicated Contacts are then
	 * removed.
	 * 
	 * @param contacts
	 *            The contacts to merge
	 * @return A new contact, the result of merging all other contacts.
	 */
	public Contact mergeDuplicateContacts(Set<Contact> contacts);
}
