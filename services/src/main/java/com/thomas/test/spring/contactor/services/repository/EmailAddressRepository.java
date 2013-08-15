package com.thomas.test.spring.contactor.services.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;

public interface EmailAddressRepository extends PagingAndSortingRepository<EmailAddress, Long> {

	public List<EmailAddress> findByContact(Contact contact);

	public Page<EmailAddress> findByContact(Contact contact, Pageable pageable);

	public Page<EmailAddress> findByContactAndEmailAddressLike(Contact contact,
			String emailAddress, Pageable pageable);

	// public Page<EmailAddress> findByContactAndLabel(Contact contact, Label
	// label, Pageable pageable);

	public List<EmailAddress> findByContactIn(Collection<Contact> contacts);

	public Page<EmailAddress> findByContactIn(Collection<Contact> contacts, Pageable pageable);

	public Page<EmailAddress> findByContactInAndEmailAddressLike(Collection<Contact> contacts,
			String emailAddress, Pageable pageable);

	// public Page<EmailAddress> findByContactInAndLabel(Collection<Contact>
	// contacts, Label label,
	// Pageable pageable);
}
