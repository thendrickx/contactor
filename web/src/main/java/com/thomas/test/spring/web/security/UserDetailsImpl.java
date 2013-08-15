package com.thomas.test.spring.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsImpl extends User {

	private static final long serialVersionUID = -2377161672533881315L;
	private String salt;

	public UserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String salt) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		setSalt(salt);
	}

	public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, String salt) {
		super(username, password, authorities);
		setSalt(salt);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(
			String salt) {
		this.salt = salt;
	}

}
