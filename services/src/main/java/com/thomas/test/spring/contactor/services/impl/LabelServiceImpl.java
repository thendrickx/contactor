package com.thomas.test.spring.contactor.services.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.Label;
import com.thomas.test.spring.contactor.domain.PhoneNumber;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.LabelService;
import com.thomas.test.spring.contactor.services.repository.ContactRepository;
import com.thomas.test.spring.contactor.services.repository.LabelRepository;

@Service("labelService")
@Repository
@Transactional
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private ContactRepository contactRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Set<Label> getLabelsForUser(User user) {
		return ImmutableSet.<Label> copyOf(labelRepository.findByUser(user));
	}

	@Override
	public Label getLabel(User user, String labelValue) {
		Label label = labelRepository.findByUserAndLabelLike(user, labelValue);
		if (label == null) {
			label = new Label();
			label.setLabel(labelValue);

			user.addLabel(label);

			em.persist(label);

		}
		return label;
	}

	@Override
	public Contact addLabelToContact(Contact contact, Label label) {
		contact.addLabel(label);
		em.persist(label);
		em.merge(contact);

		return contact;
	}

	@Override
	public EmailAddress addLabelToEmailAddress(EmailAddress emailAddress, Label label) {
		emailAddress.addLabel(label);
		em.persist(label);
		em.merge(emailAddress);

		return emailAddress;
	}

	@Override
	public Address addLabelToAddress(Address address, Label label) {
		address.addLabel(label);
		em.persist(label);
		em.merge(address);

		return address;
	}

	@Override
	public PhoneNumber addLabelToPhoneNumber(PhoneNumber phoneNumber, Label label) {
		phoneNumber.addLabel(label);
		em.persist(label);
		em.merge(phoneNumber);

		return phoneNumber;
	}

	@Override
	public Contact removeLabelFromContact(Contact contact, Label label) {
		contact.removeLabel(label);

		em.merge(contact);
		em.merge(label);

		return contact;
	}

	@Override
	public EmailAddress removeLabelFromEmailAddress(EmailAddress emailAddress, Label label) {
		emailAddress.removeLabel(label);

		em.merge(emailAddress);
		em.merge(label);

		return emailAddress;
	}

	@Override
	public Address removeLabelFromAddress(Address address, Label label) {
		address.removeLabel(label);

		em.merge(address);
		em.merge(label);

		return address;
	}

	@Override
	public PhoneNumber removeLabelFromPhoneNumber(PhoneNumber phoneNumber, Label label) {
		phoneNumber.removeLabel(label);

		em.merge(phoneNumber);
		em.merge(label);

		return phoneNumber;
	}
}
