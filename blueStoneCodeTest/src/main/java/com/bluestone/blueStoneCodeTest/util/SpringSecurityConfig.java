package com.bluestone.blueStoneCodeTest.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * Set user name and password to access the API as admin role
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("KZM").password("{noop}kzm123").roles("ADMIN");
	}

	/*
	 * the uri start with auth will only be authenticated
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("auth/**").fullyAuthenticated().and().httpBasic();
	}

	/*
	 * to encrypt password using BCryptPasswordEncoder
	 */
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}

}
