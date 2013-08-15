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

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "phoneNumber")
@Audited
public class PhoneNumber implements Serializable, Comparable<PhoneNumber> {

	private static final long serialVersionUID = 8671713781008773232L;

	@Id
	@Column(name = "phoneNumber_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "phoneNumber.phoneNumber_id",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long phoneNumberId;

	@Column(name = "phoneNumber")
	@NotNull
	@Pattern(regexp = "^(((\\+)|(00))((\\d){1,4})([\\s\\-]{1}))?([(\\d)\\-/\\s]{4,15})$",
			flags = { CASE_INSENSITIVE })
	private String phoneNumber;

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "contact_id", nullable = false, updatable = false)
	private Contact contact;

	@ManyToMany
	@JoinTable(name = "phoneNumberHasLabels", joinColumns = { @JoinColumn(name = "phoneNumber_id",
			referencedColumnName = "phoneNumber_id") }, inverseJoinColumns = { @JoinColumn(
			name = "label_id", referencedColumnName = "label_id") })
	private Set<Label> labels = new HashSet<Label>();

	public PhoneNumber() {

	}

	public Long getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(Long phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		label.getPhoneNumbers().add(this);
	}

	public void removeLabel(Label label) {
		labels.remove(label);
		label.getPhoneNumbers().remove(this);
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getPhoneNumberId());
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(PhoneNumber.class)) {
				return Objects.equal(getPhoneNumberId(), ((PhoneNumber) other).getPhoneNumberId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(PhoneNumber other) {
		if (other != null) {
			return getPhoneNumberId().compareTo(other.getPhoneNumberId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("PhoneNumber").add("Contact", getContact())
				.add("phoneNumber", getPhoneNumber()).toString();
	}

}
