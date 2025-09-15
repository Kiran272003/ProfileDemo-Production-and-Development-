//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
//	{
//		httpSecurity
//		.csrf(csrf->csrf.disable())
//		.authorizeHttpRequests(
//		auth->auth
//		 .requestMatchers("/products").hasAnyRole("USER","ADMIN")
//	        .requestMatchers("/products/**").hasAnyRole("ADMIN", "USER")
//	        .requestMatchers("/api/orders/**").hasRole("ADMIN") 
//		.anyRequest().authenticated()).httpBasic();
//		
//		return httpSecurity.build();
//	}
//	
//	@Bean
//	public UserDetailsService setUserRoles()
//	{
//		var user = User.builder()
//				.username("user")
//				.password(passwordEncoder().encode("123"))
//				.roles("USER")
//				.build();
//		
//		var admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("123"))
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user,admin);
//	}
//}
