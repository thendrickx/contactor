package com.thomas.test.spring.contactor.services.impl;

import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.ContactMergingService;

@Service("contactMergingService")
@Repository
@Transactional
public class ContactMergingServiceImpl implements ContactMergingService {

	@Override
	public Set<Set<Contact>> findPotentialDuplicatesForUser(User user) {
		return Collections.<Set<Contact>> emptySet();
	}

	@Override
	public Contact mergeDuplicateContacts(Set<Contact> contacts) {
		// TODO Auto-generated method stub
		return null;
	}

}
