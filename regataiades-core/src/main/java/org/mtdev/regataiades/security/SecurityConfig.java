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
	protected LogoutHandler mLogoutSuccessHandler;

	@Autowired
	@Qualifier("errorAEP")
	protected AuthenticationEntryPoint mErrorAEP;

	@Autowired
	@Qualifier("emptyAEP")
	protected AuthenticationEntryPoint mEmptyAEP;

	@Autowired
	protected UserDetailsService mUserDetailsService;

	@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
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
		// this.serviceConfig(http);
		// this.commonConfig(http);
		/*
		 * http.authorizeRequests().antMatchers("/**").authenticated().and()
		 * .httpBasic().authenticationEntryPoint(mEmptyAEP).and()
		 * .formLogin().successHandler(mAuthenticationSuccessHandler)
		 * .and().authorizeRequests().antMatchers("/services/**")
		 * .authenticated().and().httpBasic()
		 * .authenticationEntryPoint(mErrorAEP).and().formLogin()
		 * .successHandler(mAuthenticationSuccessHandler).and().logout()
		 * .addLogoutHandler(mLogoutSuccessHandler).and();
		 */
		http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
	}

}