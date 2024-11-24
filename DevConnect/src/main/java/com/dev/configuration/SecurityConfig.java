package com.dev.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dev.services.DevConnectUserDetailsService;

@Configuration
public class SecurityConfig {

	DevConnectUserDetailsService devConnectUserDetailsService;
	
    public SecurityConfig(DevConnectUserDetailsService devConnectUserDetailsService) {
		super();
		this.devConnectUserDetailsService = devConnectUserDetailsService;
	}

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf -> csrf.disable())
				.authenticationProvider(authenticationProviderUser())
				.authorizeHttpRequests(
						auth ->{
							auth.requestMatchers("/","/error","/favicon.ico","/home/**","/js/**", "/blog/**").permitAll();
							auth.anyRequest().authenticated();	
						}
						)
				.formLogin(login->{
					login.loginPage("/login").permitAll();
					login.defaultSuccessUrl("/home/index");
				})
				.build();
	}
    
    @Bean
    AuthenticationProvider authenticationProviderUser(){
    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
    	provider.setUserDetailsService(devConnectUserDetailsService);
    	provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    	return provider;
    }
    


}
