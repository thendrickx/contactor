package com.thomas.test.spring.contactor.domain;

import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "emailAddress")
@Audited
public class EmailAddress implements Serializable, Comparable<EmailAddress> {

	private static final long serialVersionUID = 2966577315980959534L;

	@Id
	@Column(name = "emailAddress_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "emailAddress.emailAddress_id",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long emailAddressId;

	@Column(name = "emailAddress")
	@NotNull
	@Size(min = 5, max = 255)
	@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", flags = { CASE_INSENSITIVE })
	private String emailAddress;

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "contact_id", nullable = false, updatable = false)
	private Contact contact;

	@ManyToMany
	@JoinTable(
			name = "emailAddressHasLabels",
			joinColumns = { @JoinColumn(name = "emailAddress_id",
					referencedColumnName = "emailAddress_id") },
			inverseJoinColumns = { @JoinColumn(name = "label_id", referencedColumnName = "label_id") })
	private Set<Label> labels = new HashSet<Label>();

	public EmailAddress() {

	}

	public Long getEmailAddressId() {
		return emailAddressId;
	}

	public void setEmailAddressId(Long emailAddressId) {
		this.emailAddressId = emailAddressId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public void addLabel(Label label) {
		labels.add(label);
		label.getEmailAddresses().add(this);
	}

	public void removeLabel(Label label) {
		labels.remove(label);
		label.getEmailAddresses().remove(this);
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getEmailAddressId());
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(EmailAddress.class)) {
				return Objects.equal(getEmailAddressId(),
						((EmailAddress) other).getEmailAddressId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(EmailAddress other) {
		if (other != null) {
			return getEmailAddressId().compareTo(other.getEmailAddressId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("EmailAddress").add("Contact", getContact())
				.add("emailAddress", getEmailAddress()).toString();
	}

}
