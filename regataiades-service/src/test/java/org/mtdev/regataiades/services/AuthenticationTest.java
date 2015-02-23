package org.mtdev.regataiades.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.User;
import org.mtdev.regataiades.security.UserServiceImpl;
import org.mtdev.regataiades.tools.Toolbox;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AuthenticationTest {
	protected String sLogin = "mishgunn@gmail.com";
	protected String sPassword = Toolbox.md5Spring("password");

	@InjectMocks
	protected UserDetailsService mUserDetailsService = new UserServiceImpl();

	@Mock
	UserDao mUserDao;

	@BeforeTest
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		User lUser = new User();
		lUser.setLogin(sLogin);
		lUser.setPassword(sPassword);

		Mockito.when(mUserDao.findUserByLogin("mishgunn@gmail.com"))
				.thenReturn(lUser);

		Reporter.log("Init Mockito based test");
	}

	@Test
	public void checkUserAuth() {
		UserDetails lValidUser = mUserDetailsService
				.loadUserByUsername("mishgunn@gmail.com");
		UserDetails lNullUser = null;
		try{
			lNullUser = mUserDetailsService
		
				.loadUserByUsername("bad@email");
		}catch(Exception lE){
			
		}
		Assert.assertEquals(lValidUser.getPassword(), sPassword);
		Assert.assertNull(lNullUser);
	}

}
