package com.dad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		//Publico
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/holahola").permitAll();
		http.authorizeRequests().antMatchers("/gs-guide-websocket").permitAll();
		http.authorizeRequests().antMatchers("/registro").permitAll();
		http.authorizeRequests().antMatchers("/verificacion+registro").permitAll();
		http.authorizeRequests().antMatchers("/continuacion+registro").permitAll();
		http.authorizeRequests().antMatchers("/inicio").permitAll();
		http.authorizeRequests().antMatchers("/inicio-failed").permitAll();
		
		//Privado
		http.authorizeRequests().anyRequest().authenticated();
		
		//Login
		http.formLogin().loginPage("/inicio");
		http.formLogin().usernameParameter("user");
		http.formLogin().passwordParameter("pass");
		http.formLogin().defaultSuccessUrl("/inicio+sesion");
		http.formLogin().failureUrl("/inicio-failed");
		
		
		// Logout
		http.logout().logoutUrl("/cerrar-sesion");
		http.logout().logoutSuccessUrl("/");
		
		// CSRF deshabilitado
//		http.csrf().disable();
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("mia").password("mia123").roles("USER");
		//auth.inMemoryAuthentication().withUser("kei").password("kei123").roles("USER");
		auth.authenticationProvider(authenticationProvider);
	}
}
