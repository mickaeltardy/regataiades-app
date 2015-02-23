package org.mtdev.regataiades.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
public class SecurityHandlers {
	@Bean
	public org.springframework.security.web.authentication.AuthenticationSuccessHandler authSuccessHandler() {
		return new AuthenticationSuccessHandler();
	}

	@Bean
	public org.springframework.security.web.authentication.AuthenticationFailureHandler authFailureHandler() {
		return new AuthenticationFailureHandler();
	}

	
	@Bean
	public LogoutHandler successLogoutHandler() {
		return new LogoutSuccessHandler();
	}
/*
	@Bean
	public AuthenticationEntryPoint errorAEP() {
		return new Http403ForbiddenEntryPoint();
	}
*/
	@Bean
	public AuthenticationEntryPoint emptyAEP() {
		return new EmptyAuthenticationEntryPoint();
	}
}
