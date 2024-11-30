package com.dev.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dev.services.DevConnectUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	DevConnectUserDetailsService devConnectUserDetailsService;
	RedirectAuthenticationEntrypoint redirectAuthenticationEntrypoint;
   	RedirectAuthenticationSuccess redirectAuthenticationSuccess;

	public SecurityConfig(DevConnectUserDetailsService devConnectUserDetailsService,
		RedirectAuthenticationEntrypoint redirectAuthenticationEntrypoint,
		RedirectAuthenticationSuccess redirectAuthenticationSuccess) {
		super();
		this.devConnectUserDetailsService = devConnectUserDetailsService;
		this.redirectAuthenticationEntrypoint = redirectAuthenticationEntrypoint;
		this.redirectAuthenticationSuccess = redirectAuthenticationSuccess;
	}

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf -> csrf.disable())
				.authenticationProvider(authenticationProviderUser())
				.authorizeHttpRequests(
						auth ->{
							auth.requestMatchers("/","/error","/favicon.ico","/home/**").permitAll();
							auth.requestMatchers("/js/**","/images/**","/css/**").permitAll();
							auth.anyRequest().authenticated();	
						}
						)
				.exceptionHandling(exception -> {
					exception.authenticationEntryPoint(redirectAuthenticationEntrypoint);
				})
				.formLogin(login->{
					login.loginPage("/login").permitAll();
					login.successHandler(redirectAuthenticationSuccess);
					login.defaultSuccessUrl("/");
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
