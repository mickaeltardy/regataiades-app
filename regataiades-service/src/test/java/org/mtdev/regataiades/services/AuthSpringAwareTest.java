package org.mtdev.regataiades.services;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.User;
import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { AppTestConfig.class })
@WebAppConfiguration
@ActiveProfiles(value = {"test", "wartest"})
public class AuthSpringAwareTest extends AbstractTestNGSpringContextTests {

	protected String sLogin = "mishgunn@gmail.com";
	protected String sPassword = Toolbox.md5Spring("password");

	@Autowired
	protected UserDetailsService mUserDetailsService;

	@Autowired
	UserDao mUserDao;

	@BeforeTest
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	public void prepareData() {
		User lUser = new User();
		lUser.setLogin(sLogin);
		lUser.setPassword(sPassword);

		Mockito.when(((UserDao) mUserDao).findUserByLogin("mishgunn@gmail.com"))
				.thenReturn(lUser);

	}

	@Test
	public void checkUserAuth() {
		prepareData();

		UserDetails lValidUser = mUserDetailsService
				.loadUserByUsername("mishgunn@gmail.com");
		UserDetails lNullUser = null;
		try {
			lNullUser = mUserDetailsService.loadUserByUsername("bad@email");
		} catch (Exception lE) {

		}
		Assert.assertEquals(lValidUser.getPassword(), sPassword);
		Assert.assertNull(lNullUser);
	}

}