package com.thomas.test.spring.contactor.services;

import java.util.Set;

import com.thomas.test.spring.contactor.domain.Address;
import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.EmailAddress;
import com.thomas.test.spring.contactor.domain.Label;
import com.thomas.test.spring.contactor.domain.PhoneNumber;
import com.thomas.test.spring.contactor.domain.User;

public interface LabelService {

	public Set<Label> getLabelsForUser(User user);

	public Label getLabel(User user, String labelValue);

	public Contact addLabelToContact(Contact contact, Label label);

	public EmailAddress addLabelToEmailAddress(EmailAddress emailAddress, Label label);

	public Address addLabelToAddress(Address address, Label label);

	public PhoneNumber addLabelToPhoneNumber(PhoneNumber phoneNumber, Label label);

	public Contact removeLabelFromContact(Contact contact, Label label);

	public EmailAddress removeLabelFromEmailAddress(EmailAddress emailAddress, Label label);

	public Address removeLabelFromAddress(Address address, Label label);

	public PhoneNumber removeLabelFromPhoneNumber(PhoneNumber phoneNumber, Label label);

}