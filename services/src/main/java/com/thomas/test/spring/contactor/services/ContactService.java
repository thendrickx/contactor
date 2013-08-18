package com.thomas.test.spring.contactor.services;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Comment;
import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.PhoneNumber;
import com.thomas.test.spring.contactor.domain.User;

public interface ContactService {

	public Contact save(
			User user,
			Contact contact);

	public Set<Contact> findAllContactsForUser(
			User user);

	public Page<Contact> findAllContactsForUser(
			User user,
			Pageable pageable);

	// ////////////////////////////////
	// ADD
	// ////////////////////////////////

	public Contact addCommentToContact(
			Contact contact,
			Comment comment);

	public Contact addEmailAddressToContact(
			Contact contact,
			EmailAddress emailAddress);

	public Contact addAddressToContact(
			Contact contact,
			Address address);

	public Contact addPhoneNumberToContact(
			Contact contact,
			PhoneNumber phoneNumber);

	// ////////////////////////////////
	// REMOVE
	// ////////////////////////////////

	public Contact removeCommentFromContact(
			Contact contact,
			Comment comment);

	public Contact removeEmailAddressFromContact(
			Contact contact,
			EmailAddress emailAddress);

	public Contact removeAddressFromContact(
			Contact contact,
			Address address);

	public Contact removePhoneNumberFromContact(
			Contact contact,
			PhoneNumber phoneNumber);

	// ////////////////////////////////
	// MODIFY
	// ////////////////////////////////

	public void modifyComment(
			Comment comment);

	public void modifyEmailAddress(
			EmailAddress emailAddress);

	public void modifyAddress(
			Address address);

	public void modifyPhoneNumber(
			PhoneNumber phoneNumber);

	/**
	 * Goes through each of the contact's attribute (address, email, comment,
	 * phone numbers etc. and removes the empty ones.
	 * 
	 * @param contact
	 */
	public void removeEmptyAttributes(
			Contact contact);
}
