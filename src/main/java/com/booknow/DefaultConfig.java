package com.booknow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultConfig {

	@Bean
	public PasswordEncoder setupBcrypt() {
		return new BCryptPasswordEncoder();
	}
}
