package com.dev.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class RedirectAuthenticationSuccess implements AuthenticationSuccessHandler {

	public RedirectAuthenticationSuccess() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("In success handler");
		if(response.getHeader("Content-Type").equals("application/json")) {
			response.sendRedirect("/");
		}
	}

}
