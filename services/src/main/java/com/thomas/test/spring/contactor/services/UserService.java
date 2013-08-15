package com.thomas.test.spring.contactor.services;

import java.util.Collection;

import com.thomas.test.spring.contactor.domain.User;

public interface UserService {

	public User createUser(String emailAddress, String plainPasword);

	public User save(User user);

	public void changePassword(User user, String newPassword);

	public Collection<User> findAll();

	public User findByEmailAddress(String emailAddress);

}
