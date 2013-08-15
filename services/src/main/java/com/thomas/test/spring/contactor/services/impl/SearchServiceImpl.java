package com.thomas.test.spring.contactor.services.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.Label;
import com.thomas.test.spring.contactor.domain.PhoneNumber;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.SearchService;
import com.thomas.test.spring.contactor.services.repository.AddressRepository;
import com.thomas.test.spring.contactor.services.repository.ContactRepository;
import com.thomas.test.spring.contactor.services.repository.EmailAddressRepository;
import com.thomas.test.spring.contactor.services.repository.PhoneNumberRepository;

@Service("searchService")
@Repository
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private EmailAddressRepository emailAddressRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PhoneNumberRepository phoneNumberRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Contact> findContactsByFirstNameOrLastName(User user, String searchCriteria,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Contact> findContactByLabel(User user, Label label, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<EmailAddress> findEmailAddresses(User user, String searchCriteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<EmailAddress> findEmailAddressesByLabel(User user, Label label, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Address> findAddresses(User user, String searchCriteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Address> findAddressesByLabel(User user, Label label, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PhoneNumber> findPhoneNumbers(User user, String searchCriteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PhoneNumber> findPhoneNumbersByLabel(User user, Label label, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
