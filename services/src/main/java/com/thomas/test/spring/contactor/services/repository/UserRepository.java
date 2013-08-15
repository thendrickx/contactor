package com.thomas.test.spring.contactor.services.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thomas.test.spring.contactor.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public User findByName(String name);

	public User findByEmailAddress(String emailAddress);
}
