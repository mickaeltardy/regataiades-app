package org.mtdev.regataiades.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(value = SecurityHandlers.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler mAuthenticationSuccessHandler;

	@Autowired
	protected AuthenticationFailureHandler mAuthenticationFailureHandler;

	@Autowired
	protected LogoutHandler mLogoutSuccessHandler;

	@Autowired
	@Qualifier("emptyAEP")
	protected AuthenticationEntryPoint mEmptyAEP;

	@Autowired
	protected UserDetailsService mUserDetailsService;

	@Override
	protected UserDetailsService userDetailsService() {
		return mUserDetailsService;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		auth.userDetailsService(mUserDetailsService).passwordEncoder(
				passwordEncoder);

	}

	/**
	 * TODO Split http confs
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/**").permitAll().and()
				.httpBasic().authenticationEntryPoint(mEmptyAEP).and()
				.formLogin().successHandler(mAuthenticationSuccessHandler)
				.failureHandler(mAuthenticationFailureHandler).and()
				.authorizeRequests().antMatchers("/registration/**")
				.authenticated().and().httpBasic().and().exceptionHandling()
				.accessDeniedPage("/errors/403").and().formLogin()
				.successHandler(mAuthenticationSuccessHandler).and().logout()
				.logoutSuccessUrl("/auth/logout")
				.addLogoutHandler(mLogoutSuccessHandler).and().formLogin()
				.and().csrf().disable();

	}

}