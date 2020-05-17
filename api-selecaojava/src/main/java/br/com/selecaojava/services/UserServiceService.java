package br.com.selecaojava.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.selecaojava.security.UserSS;

public class UserServiceService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
