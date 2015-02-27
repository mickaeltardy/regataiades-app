package org.mtdev.regataiades.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest pRequest,
			HttpServletResponse pResponse, AuthenticationException pException)
			throws IOException, ServletException {
		pResponse = Toolbox.enrichResponse(pResponse);
		pResponse.getWriter().println(
				"{\"status\": \"failure\", \"details\" : \"auth failure\"}");
	}


}