package com.thomas.test.spring.contactor.services.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.PhoneNumber;

public interface PhoneNumberRepository extends PagingAndSortingRepository<PhoneNumber, Long> {

	public List<PhoneNumber> findByContact(Contact contact);

	public Page<PhoneNumber> findByContact(Contact contact, Pageable pageable);

	// public Page<PhoneNumber> findByContactAndLabel(Contact contact, Label
	// label, Pageable pageable);

	public Page<PhoneNumber> findByContactAndPhoneNumberLike(Contact contact, String phoneNumber,
			Pageable pageable);

	public List<PhoneNumber> findByContactIn(Collection<Contact> contacts);

	public Page<PhoneNumber> findByContactIn(Collection<Contact> contacts, Pageable pageable);

	// public Page<PhoneNumber> findByContactInAndLabel(Collection<Contact>
	// contacts, Label label,
	// Pageable pageable);

	public Page<PhoneNumber> findByContactInAndPhoneNumberLike(Collection<Contact> contacts,
			String phoneNumber, Pageable pageable);

}
