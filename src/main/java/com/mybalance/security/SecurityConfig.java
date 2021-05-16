package com.mybalance.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/info").permitAll()
			.antMatchers("/style.css").permitAll()
			.antMatchers("/adminPage/**/").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()			
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.loginProcessingUrl("/processlogin")
        .permitAll()
        .failureUrl("/login?error=true")
    .usernameParameter("user")
    .passwordParameter("pass")
    .and()
.logout()
    .logoutUrl("/logmeout")
        .logoutSuccessUrl("/")
        .logoutSuccessUrl("/")
        .and()
        .exceptionHandling()
        .accessDeniedPage("/");
	}	
}