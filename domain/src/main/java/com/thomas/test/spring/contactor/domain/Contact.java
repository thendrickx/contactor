package com.thomas.test.spring.contactor.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "contact")
@Audited
public class Contact implements Serializable, Comparable<Contact> {

	private static final long serialVersionUID = 6460601127562902771L;

	@Id
	@Column(name = "contact_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "contact.contact_id",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long contactId;

	@Column(name = "firstName")
	@NotNull
	@Size(min = 1, max = 50)
	private String firstName;

	@Column(name = "lastName")
	@Size(min = 1, max = 50)
	private String lastName;

	@Column(name = "picture")
	@Basic
	@Lob
	private byte[] picture;

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
	private Set<EmailAddress> emailAddresses = new HashSet<EmailAddress>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
	private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
	private Set<Address> addresses = new HashSet<Address>();

	@ManyToMany
	@JoinTable(name = "contactHasLabels", joinColumns = { @JoinColumn(name = "contact_id",
			referencedColumnName = "contact_id") }, inverseJoinColumns = { @JoinColumn(
			name = "label_id", referencedColumnName = "label_id") })
	private Set<Label> labels = new HashSet<Label>();

	public Contact() {

	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setContact(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setContact(null);
	}

	public Set<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public void addEmailAddress(EmailAddress emailAddress) {
		emailAddresses.add(emailAddress);
		emailAddress.setContact(this);
	}

	public void removeEmailAddress(EmailAddress emailAddress) {
		emailAddresses.remove(emailAddress);
		emailAddress.setContact(null);
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void addPhoneNumber(PhoneNumber phoneNumber) {
		phoneNumbers.add(phoneNumber);
		phoneNumber.setContact(this);
	}

	public void removePhoneNumber(PhoneNumber phoneNumber) {
		phoneNumbers.remove(phoneNumber);
		phoneNumber.setContact(null);
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses.add(address);
		address.setContact(this);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
		address.setContact(null);
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public void addLabel(Label label) {
		labels.add(label);
		label.getContacts().add(this);
	}

	public void removeLabel(Label label) {
		labels.remove(label);
		label.getContacts().remove(this);
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getContactId());
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(Contact.class)) {
				return Objects.equal(getContactId(), ((Contact) other).getContactId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(Contact other) {
		if (other != null) {
			return getContactId().compareTo(other.getContactId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("Contact").add("firstName", getFirstName())
				.add("lastName", getLastName()).toString();
	}
}
