package com.dad;

import com.dad.SecurityConfiguration;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository usuarioRepositorio;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// System.out.println("-- VAMOS A VERIFICAR ESTE USUARIO authentication provider --");
		Usuario usuario = usuarioRepositorio.findByLogin(authentication.getName());
		
		String password = (String) authentication.getCredentials();
		if(!new BCryptPasswordEncoder().matches(password, usuario.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		return (Authentication) new UsernamePasswordAuthenticationToken(usuario.getLogin(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
}
