package com.thomas.test.spring.contactor.services.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableSet;
import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.SaltService;
import com.thomas.test.spring.contactor.services.UserService;
import com.thomas.test.spring.contactor.services.repository.UserRepository;

@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SaltService saltService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PersistenceContext
	private EntityManager em;

	@Override
	public User createUser(String emailAddress, String plainPasword) {
		User user = new User();

		user.setEmailAddress(emailAddress);
		String salt = saltService.getNewSaltAsAString();
		user.setPassword(passwordEncoder.encodePassword(plainPasword, salt));
		user.setSalt(salt);

		return user;
	}

	@Override
	public User save(User user) {
		if (user.getUserId() == null) {
			String salt = saltService.getNewSaltAsAString();
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), salt));
			user.setSalt(salt);
			em.persist(user);
		} else {
			em.merge(user);
		}
		return user;
	}

	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encodePassword(newPassword, user.getSalt()));

		save(user);
	}

	@Override
	public Collection<User> findAll() {
		return ImmutableSet.<User> copyOf(userRepository.findAll());
	}

	@Override
	public User findByEmailAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}
}
