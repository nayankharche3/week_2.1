package com.examoe.securitydb2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringBootSecurityConfig extends WebSecurityConfigurerAdapter {

	// Authentication : set user/password details and mention the role
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("someone1").password("pass").roles("USER")
				.and().withUser("someone2").password("pass").roles("USER", "ADMIN")
				.and().withUser("slr").password("pass").roles("SELR")
				.and().withUser("clr").password("pass").roles("CALLER");
	}

	// Authorization : mention which role can access which URL
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/userlogin?", "/xyz").hasRole("USER")
		.antMatchers("/adminlogin").hasRole("ADMIN")
		.antMatchers("/slrlogin").hasAnyRole("USER", "SELR", "ADMIN")
		.antMatchers("/callerlogin").hasAnyRole("CALLER")
		.antMatchers("/**").denyAll()
		.and()
		.logout().logoutUrl("/logout").deleteCookies("remove").invalidateHttpSession(false).and().csrf()
		.disable().headers().frameOptions().disable();
	}
}
