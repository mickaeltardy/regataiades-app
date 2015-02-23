package org.mtdev.regataiades.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest pRequest,
			HttpServletResponse pResponse, AuthenticationException pException)
			throws IOException, ServletException {
		pResponse.addHeader("Access-Control-Allow-Origin", "*");
		pResponse.addHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		pResponse.addHeader("Access-Control-Max-Age", "3600");
		pResponse.addHeader(
				"Access-Control-Allow-Headers",
				"x-requested-with, Content-Type, origin, authorization, accept, client-security-token");
		pResponse.getWriter().println(
				"{\"status\": \"failure\", \"details\" : \"auth failure\"}");
	}


}