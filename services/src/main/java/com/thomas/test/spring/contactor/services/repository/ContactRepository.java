package com.thomas.test.spring.contactor.services.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thomas.test.spring.contactor.domain.Contact;
import com.thomas.test.spring.contactor.domain.User;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

	public List<Contact> findByUser(User user);

	public Page<Contact> findByUser(User user, Pageable pageable);

	public Page<Contact> findByUserAndFirstNameLike(User user, String firstName, Pageable pageable);

	public Page<Contact> findByUserAndLastNameLike(User user, String lastName, Pageable pageable);
}
