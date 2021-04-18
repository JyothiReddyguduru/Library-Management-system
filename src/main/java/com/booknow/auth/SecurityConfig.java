package com.booknow.auth;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Program to disable csrf and authorize all requests
 * @author Jyothi and Lasya
 * @Last Modified On 11/27/2020
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Program to disable csrf and authorize all requests
	 * @author Jyothi and Lasya
	 *
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//auth.inMemoryAuthentication().withUser("jyothi").password(passwordEncoder.encode("jyothi")).roles("LMS_USER")
				//.and().passwordEncoder(passwordEncoder);
		
		//http.httpBasic().and().authorizeRequests()
		//.antMatchers("/appconfig/**").hasAuthority("ADMIN")
		//.anyRequest().authenticated()
				//.and().logout().invalidateHttpSession(true)
               // .logoutUrl("/logout").logoutSuccessUrl("/index.html")
				//.and().csrf().disable();
		
		//http.authorizeRequests().anyRequest().hasAnyRole("STUDENT", "ADMIN").and().httpBasic();
		
		http
        .csrf().disable()
        .authorizeRequests()
            .anyRequest().permitAll();
		
	}
	
	/**
	 * Program to disable csrf and authorize all requests
	 * @author Jyothi and Lasya
	 *
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("jyothi").password(passwordEncoder.encode("jyothi")).roles("LMS_USER")
		.and().passwordEncoder(passwordEncoder);
		
		//use .passwordEncoder(passwordEncoder()) method to hash the entered password
		//auth.jdbcAuthentication().dataSource(datasource)
		//.passwordEncoder(passwordEncoder)
				//.usersByUsernameQuery("select username, password from user where username=?")
				//.authoritiesByUsernameQuery("select username, roles from user where username=?")
				//.rolePrefix("");

	}
	

}
