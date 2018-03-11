package com.dad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure (HttpSecurity http) throws Exception{
		//Publico
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/inicio").permitAll();
		
		//Privado
		http.authorizeRequests().anyRequest().authenticated();
		
		//Login
		http.formLogin().loginPage("/inicio+sesion");
		http.formLogin().usernameParameter("usuario");
		http.formLogin().passwordParameter("contrasena");
		http.formLogin().defaultSuccessUrl("/inicio+sesion");
		http.formLogin().failureUrl("/");
		
		
		// Logout
		http.logout().logoutUrl("/cerrar-sesion");
		http.logout().logoutSuccessUrl("/");
		// CSRF deshabilitado
		http.csrf().disable();
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("mia").password("mia123").roles("USER");
		
	}
}
