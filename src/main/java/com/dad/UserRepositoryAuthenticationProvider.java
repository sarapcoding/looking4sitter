package com.dad;

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
	
	//@Override
	/*
	public Authentication autenticate(Authentication auth) throws AuthenticationException{
		List<Usuario> list_usuario = usuarioRepositorio.findByLogin(auth.getName());
		if (list_usuario.isEmpty()) {
			throw new BadCredentialsException("User not found");
		}
		Usuario usuario = list_usuario.get(0);
		
		String password = (String) auth.getCredentials();
		if(!new BCryptPasswordEncoder().matches(password, usuario.getPasswordHash())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		return new UsernamePasswordAuthenticationToken(usuario.getLogin(), password, roles);
	}
	*/

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		List<Usuario> list_usuario = usuarioRepositorio.findByLogin(authentication.getName());
		if (list_usuario.isEmpty()) {
			throw new BadCredentialsException("User not found");
		}
		Usuario usuario = list_usuario.get(0);
		
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
		// TODO Auto-generated method stub
		return false;
	}

	
}
