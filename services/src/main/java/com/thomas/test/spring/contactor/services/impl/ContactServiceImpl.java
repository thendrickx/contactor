package com.thomas.test.spring.contactor.services.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Comment;
import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.PhoneNumber;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.ContactService;
import com.thomas.test.spring.contactor.services.repository.ContactRepository;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Contact save(User user, Contact contact) {
		user = em.<User> merge(user);
		user.addContact(contact);
		if (contact.getContactId() == null) {
			// Insert new transient object
			em.persist(contact);
		} else {
			// update existing object
			em.merge(contact);
		}

		return contact;
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Contact> findAllContactsForUser(User user) {
		return ImmutableSet.<Contact> copyOf(contactRepository.findByUser(user));
	}

	@Override
	public Page<Contact> findAllContactsForUser(User user, Pageable pageable) {
		return contactRepository.findByUser(user, pageable);
	}

	// ////////////////////////////////
	// ADD
	// ////////////////////////////////

	@Override
	public Contact addCommentToContact(Contact contact, Comment comment) {
		contact.addComment(comment);
		em.persist(comment);
		em.merge(contact);

		return contact;
	}

	@Override
	public Contact addEmailAddressToContact(Contact contact, EmailAddress emailAddress) {
		contact.addEmailAddress(emailAddress);
		em.persist(emailAddress);
		em.merge(contact);

		return contact;
	}

	@Override
	public Contact addAddressToContact(Contact contact, Address address) {
		contact.addAddress(address);
		em.persist(address);
		em.merge(contact);

		return contact;
	}

	@Override
	public Contact addPhoneNumberToContact(Contact contact, PhoneNumber phoneNumber) {
		contact.addPhoneNumber(phoneNumber);
		em.persist(phoneNumber);
		em.merge(contact);

		return contact;
	}

	// ////////////////////////////////
	// REMOVE
	// ////////////////////////////////

	@Override
	public Contact removeCommentFromContact(Contact contact, Comment comment) {
		contact.removeComment(comment);
		em.merge(contact);

		return contact;
	}

	@Override
	public Contact removeEmailAddressFromContact(Contact contact, EmailAddress emailAddress) {
		contact.removeEmailAddress(emailAddress);
		em.merge(contact);

		return contact;
	}

	@Override
	public Contact removeAddressFromContact(Contact contact, Address address) {
		contact.removeAddress(address);
		em.merge(contact);

		return contact;
	}

	@Override
	public Contact removePhoneNumberFromContact(Contact contact, PhoneNumber phoneNumber) {
		contact.removePhoneNumber(phoneNumber);
		em.merge(contact);

		return contact;
	}

	// ////////////////////////////////
	// MODIFY
	// ////////////////////////////////

	@Override
	public void modifyComment(Comment comment) {
		em.merge(comment);
	}

	@Override
	public void modifyEmailAddress(EmailAddress emailAddress) {
		em.merge(emailAddress);
	}

	@Override
	public void modifyAddress(Address address) {
		em.merge(address);
	}

	@Override
	public void modifyPhoneNumber(PhoneNumber phoneNumber) {
		em.merge(phoneNumber);
	}

}
