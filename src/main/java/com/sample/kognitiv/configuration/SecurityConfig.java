package com.sample.kognitiv.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin("*")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("guest").password("{noop}guest1234").roles("USER");
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.httpBasic().and().authorizeRequests()
	        .antMatchers("/collect/*").hasRole("USER")
	        .and().csrf().disable();
	}

	

	
}
