package com.dg.testbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/users").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
		.and().httpBasic()
		.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
