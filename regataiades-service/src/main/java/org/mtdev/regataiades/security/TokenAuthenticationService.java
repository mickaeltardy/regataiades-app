package org.mtdev.regataiades.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.mtdev.regataiades.model.User;
import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	// check
	// https://github.com/Robbert1/boot-stateless-auth/tree/master/src/main/java/com/jdriven/stateless/security
	// private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

	private final TokenHandler tokenHandler;

	@Autowired
	protected UserDetailsService mUserDetailsService;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(
				DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse pResponse,
			UserAuthentication pAuthentication) {
		final UserDetails lUser = pAuthentication.getDetails();
		pResponse = Toolbox.enrichResponse(pResponse);
		pResponse.addHeader("Content-type", "application/json");
		// Unable to put it into the header since jquery cannot read it
		/*
		 * 
		 * pResponse.addHeader(AUTH_HEADER_NAME,
		 * tokenHandler.createTokenForUser(lUser));
		 */
		try {
			pResponse.getWriter().println(
					"{\"status\": \"success\", \"token\" : \""
							+ tokenHandler.createTokenForUser(lUser) + "\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			User lUser = tokenHandler.parseUserFromToken(token);

			if (lUser != null) {
				UserDetails lUserDetails = mUserDetailsService
						.loadUserByUsername(lUser.getLogin());
				return new UserAuthentication(lUserDetails);
			}
		}
		return null;
	}
}