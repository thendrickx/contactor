package com.thomas.test.spring.web.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thomas.test.spring.contactor.domain.User;
import com.thomas.test.spring.contactor.services.UserService;
import com.thomas.test.spring.web.security.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(
			String username) throws UsernameNotFoundException {

		User user = userService.findByEmailAddress(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (user != null) {
			String password = new String(user.getPassword());
			String salt = user.getSalt();
			authorities.add(new SimpleGrantedAuthority("authenticated"));

			return new UserDetailsImpl(username, password, authorities, salt);
		}

		throw new UsernameNotFoundException("User " + username + " not found");
	}
}
