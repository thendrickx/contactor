package com.thomas.test.spring.contactor.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.Label;
import com.thomas.test.spring.contactor.domain.PhoneNumber;
import com.thomas.test.spring.contactor.domain.User;

public interface SearchService {

	public Page<Contact> findContactsByFirstNameOrLastName(User user, String searchCriteria,
			Pageable pageable);

	public Page<Contact> findContactByLabel(User user, Label label, Pageable pageable);

	public Page<EmailAddress> findEmailAddresses(User user, String searchCriteria, Pageable pageable);

	public Page<EmailAddress> findEmailAddressesByLabel(User user, Label label, Pageable pageable);

	public Page<Address> findAddresses(User user, String searchCriteria, Pageable pageable);

	public Page<Address> findAddressesByLabel(User user, Label label, Pageable pageable);

	public Page<PhoneNumber> findPhoneNumbers(User user, String searchCriteria, Pageable pageable);

	public Page<PhoneNumber> findPhoneNumbersByLabel(User user, Label label, Pageable pageable);
}
