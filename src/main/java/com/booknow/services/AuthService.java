package com.booknow.services;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.booknow.entities.Principal;


@Service
public class AuthService {

	public Principal getLoggedInUser() {
		return getLoggedInUserAsOptional().orElseThrow(() -> new RuntimeException("Current session is anonymous or not an instance of Principal"));
	}
	
	public Optional<Principal> getLoggedInUserAsOptional() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(e -> e.getAuthentication())
				.map(e -> e.getPrincipal())
				.filter(Principal.class::isInstance)
				.map(Principal.class::cast);
	}

	
}
