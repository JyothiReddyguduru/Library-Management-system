package com.booknow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Defualt encoder for passwrods
 * @author Jyothi
 * @Last Modified On 11/10/2020
 *
 */
@Configuration
public class DefaultConfig {

	@Bean
	public PasswordEncoder setupBcrypt() {
		return new BCryptPasswordEncoder();
	}
}
