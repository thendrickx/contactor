package com.thomas.test.spring.contactor.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.dbunit.DataSourceDatabaseTester;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.thomas.test.spring.contactor.domain.config.DomainTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DomainTestConfig.class })
@ActiveProfiles("test")
@Transactional
public abstract class AbstractDomainImplTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected Logger logger = Logger.getLogger(getClass());

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected DataSourceDatabaseTester databaseTester;

	@Autowired
	protected Validator validator;

	public User createNewUser() {
		User user = new User();

		user.setName("Thomas");
		user.setEmailAddress("thomas@test.com");
		user.setPassword("password");
		user.setSalt("salt");

		return user;
	}

	public Contact createNewContact() {
		Contact contact = new Contact();

		contact.setFirstName("Bob");
		contact.setLastName("Kelso");

		return contact;
	}

	public Comment createNewComment() {
		Comment comment = new Comment();

		comment.setComment("ALLO MOTO");

		return comment;
	}

	public EmailAddress createNewEmailAddress() {
		EmailAddress address = new EmailAddress();

		address.setEmailAddress("thomas@test.com");

		return address;
	}

	public PhoneNumber createNewPhoneNumber() {
		PhoneNumber phoneNumber = new PhoneNumber();

		phoneNumber.setPhoneNumber("061122334455");

		return phoneNumber;
	}

	public Label createNewLabel(String labelString) {
		Label label = new Label();
		label.setLabel(labelString);
		return label;
	}

	public Address createNewAddress() {
		Address address = new Address();

		address.setCountry("France");
		address.setCity("Paris");
		address.setStreet("Rue Bob");
		address.setStreetNumber(12);
		address.setStreetNumberQualifier("bis");

		return address;
	}

	public <T> List<T> getAllInstances(Class<T> clasz) {
		CriteriaBuilder builder = em.getEntityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clasz);

		Root<T> root = criteria.from(clasz);
		criteria.select(root);
		List<T> instances = em.createQuery(criteria).getResultList();

		return instances;
	}

}
