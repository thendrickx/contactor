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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.google.common.base.Objects;

@Entity
@Table(name = "address")
@Audited
public class Address implements Serializable, Comparable<Address> {

	private static final long serialVersionUID = 3854178914973438679L;

	@Id
	@Column(name = "address_id")
	@TableGenerator(name = "idGenerator", table = "idGenerator", pkColumnName = "primaryKeyName",
			valueColumnName = "primaryKeyValue", pkColumnValue = "Address.address_id",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	private Long addressId;

	@Column(name = "country")
	@NotNull
	@Size(min = 3, max = 45)
	private String country;

	@Column(name = "state")
	@Size(max = 45)
	private String state;

	@Column(name = "city")
	@NotNull
	@Size(min = 3, max = 45)
	private String city;

	@Column(name = "district")
	@Size(max = 45)
	private String district;

	@Column(name = "street")
	@NotNull
	@Size(min = 3, max = 45)
	private String street;

	@Column(name = "streetNumber")
	@NotNull
	private Integer streetNumber;

	@Column(name = "streetNumberQualifier")
	@Size(min = 1, max = 5)
	private String streetNumberQualifier;

	@Column(name = "zipCode")
	@NotNull
	private Integer zipCode;

	@Column(name = "companyName")
	@Size(max = 45)
	private String companyName;

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "contact_id", nullable = false, updatable = false)
	private Contact contact;

	@ManyToMany
	@JoinTable(name = "emailAddressHasLabels", joinColumns = { @JoinColumn(name = "address_id",
			referencedColumnName = "address_id") }, inverseJoinColumns = { @JoinColumn(
			name = "label_id", referencedColumnName = "label_id") })
	private Set<Label> labels = new HashSet<Label>();

	public Address() {

	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetNumberQualifier() {
		return streetNumberQualifier;
	}

	public void setStreetNumberQualifier(String streetNumberQualifier) {
		this.streetNumberQualifier = streetNumberQualifier;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
		label.getAddresses().add(this);
	}

	public void removeLabel(Label label) {
		labels.remove(label);
		label.getAddresses().remove(this);
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getAddressId());
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null) {
			if (other.getClass().isAssignableFrom(Address.class)) {
				return Objects.equal(getAddressId(), ((Address) other).getAddressId());
			}
		}

		return false;
	}

	@Override
	public int compareTo(Address other) {
		if (other != null) {
			return getAddressId().compareTo(other.getAddressId());
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper("Address").add("Contact", getContact())
				.add("Country", getCountry()).add("City", getCity()).add("Street", getStreet())
				.add("StreetNumber", getStreetNumber()).toString();
	}

}
