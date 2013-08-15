package com.thomas.test.spring.contactor.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "label", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "label" }))
@Audited
public class Label implements Serializable, Comparable<Label> {

	private static final long serialVersionUID = -2961076603199744195L;

	@Id
	@Column(name = "label_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "label.label_id",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long labelId;

	@Column(name = "label")
	@NotNull
	@Size(min = 1, max = 45)
	private String label;

	@ManyToMany
	@JoinTable(name = "contactHasLabels", joinColumns = { @JoinColumn(name = "label_id",
			referencedColumnName = "label_id") }, inverseJoinColumns = { @JoinColumn(
			name = "contact_id", referencedColumnName = "contact_id") })
	private Set<Contact> contacts = new HashSet<Contact>();

	@ManyToMany
	@JoinTable(name = "emailAddressHasLabels", joinColumns = { @JoinColumn(name = "label_id",
			referencedColumnName = "label_id") }, inverseJoinColumns = { @JoinColumn(
			name = "emailAddress_id", referencedColumnName = "emailAddress_id") })
	private Set<EmailAddress> emailAddresses = new HashSet<EmailAddress>();

	@ManyToMany
	@JoinTable(name = "phoneNumberHasLabels", joinColumns = { @JoinColumn(name = "label_id",
			referencedColumnName = "label_id") }, inverseJoinColumns = { @JoinColumn(
			name = "phoneNumber_id", referencedColumnName = "phoneNumber_id") })
	private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

	@ManyToMany
	@JoinTable(name = "addressHasLabels", joinColumns = { @JoinColumn(name = "label_id",
			referencedColumnName = "label_id") }, inverseJoinColumns = { @JoinColumn(
			name = "address_id", referencedColumnName = "address_id") })
	private Set<Address> addresses = new HashSet<Address>();

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

	public Label() {

	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getLabelId());
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(Label.class)) {
				return Objects.equal(getLabelId(), ((Label) other).getLabelId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(Label other) {
		if (other != null) {
			return getLabelId().compareTo(other.getLabelId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("Label").add("value", getLabel()).toString();
	}
}
