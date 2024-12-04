package com.dev.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
