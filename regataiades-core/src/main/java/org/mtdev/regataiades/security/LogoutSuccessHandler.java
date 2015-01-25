package org.mtdev.regataiades.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class LogoutSuccessHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest pRequest,
			HttpServletResponse pResponse, Authentication pAuthentication) {
		try {
			pResponse.getWriter().println("{\"status\": \"logout\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}