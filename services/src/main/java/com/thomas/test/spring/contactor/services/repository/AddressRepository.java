package com.thomas.test.spring.contactor.services.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Contact;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	public List<Address> findByContact(Contact contact);

	public List<Address> findByContactIn(Collection<Contact> contacts);

	public Page<Address> findByContact(Contact contact, Pageable pageable);

	public Page<Address> findByContactIn(Collection<Contact> contacts, Pageable pageable);

	// @Query("")
	// TODO: write QUERY !
	// public Page<Address> findByContactAndCriteria(Contact contact, String
	// criteria, Pageable pageable);

	// public Page<Address> findByContactAndLabel(Contact contact, Label label,
	// Pageable pageable);
}
