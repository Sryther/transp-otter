package com.ynov.transpotter.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

public class User extends SocialUser {
	private static final long serialVersionUID = 5034323875945624521L;

	public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	// TODO implements features
}
