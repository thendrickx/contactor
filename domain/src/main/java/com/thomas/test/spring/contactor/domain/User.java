package com.thomas.test.spring.contactor.domain;

import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "user")
@Audited
public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = 4658211129789403207L;

	@Id
	@Column(name = "user_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "user.user_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long userId;

	@Column(name = "name")
	@NotNull
	@Size(min = 2, max = 45)
	private String name;

	@Column(name = "password")
	@NotNull
	@Size(min = 6, max = 255)
	@Basic
	private String password;

	@Column(name = "salt")
	@NotNull
	@Basic
	private String salt;

	@Column(name = "emailAddress", unique = true)
	@NotNull
	@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", flags = { CASE_INSENSITIVE })
	private String emailAddress;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private Set<Contact> contacts = new HashSet<Contact>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private Set<Label> labels = new HashSet<Label>();

	public User() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		contacts.add(contact);
		contact.setUser(this);
	}

	public void removeContact(Contact contact) {
		contacts.remove(contact);
		contact.setUser(null);
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void addLabel(Label label) {
		labels.add(label);
		label.setUser(null);
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	@Override
	public int hashCode() {
		if (getUserId() != null) {
			return getUserId().hashCode();
		} else {
			return User.class.hashCode();
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(User.class)) {
				return Objects.equal(getUserId(), ((User) other).getUserId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(User other) {
		if (other != null) {
			return getUserId().compareTo(other.getUserId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("User").add("name", getName())
				.add("emailAddress", getEmailAddress()).toString();
	}
}
