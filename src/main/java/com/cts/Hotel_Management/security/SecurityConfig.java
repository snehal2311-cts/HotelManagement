package com.cts.Hotel_Management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.cts.Hotel_Management.service.CustomUserDetailsService;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	private final CustomUserDetailsService customUserDetailsService;
	
	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService=customUserDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChaoin(HttpSecurity http ) throws Exception{
		http.csrf(httpSecurityCsrfConfigurer->httpSecurityCsrfConfigurer.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/admin/**").hasRole("ADMIN")
				.requestMatchers("/api/user/**").permitAll()
				.requestMatchers("/api/rooms/**").permitAll()
//				.anyRequest().authenticated()
				)
		.httpBasic(Customizer.withDefaults());
//		.formLogin(httpSecurityFormLoginConfigurer ->httpSecurityFormLoginConfigurer.permitAll());
		return http.build();
	}
	
//	@Bean
//	public AuthenticationManager authManager(HttpSecurity http,PasswordEncoder passwordEncoder) throws Exception{
//		return http.getSharedObject(AuthenticationManagerBuilder.class)
//				.userDetailsService(customUserDetailsService)
//				.passwordEncoder(passwordEncoder)
//				.and()
//				.build();
//	}
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
