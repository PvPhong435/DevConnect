package com.dev.configuration;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RedirectAuthenticationEntrypoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("Using redirectauthenticationentry");
		String contentTypeHeader=request.getHeader("Content-Type");
		System.out.println(contentTypeHeader.contains("application/json"));
		if(contentTypeHeader !=null && contentTypeHeader.contains("application/json")) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("{\"redirect\": \"/login\", \"message\": \"You must log in first\"}");
		}else {
			response.sendRedirect("/login");
		}
	}

}
