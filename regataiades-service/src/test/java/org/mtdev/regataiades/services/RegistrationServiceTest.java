package org.mtdev.regataiades.services;

import org.mtdev.regataiades.business.interfaces.DataProcessor;
import org.mtdev.regataiades.business.interfaces.RegistrationManager;
import org.mtdev.regataiades.business.interfaces.UsersManager;
import org.mtdev.regataiades.config.AppConfig;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class RegistrationServiceTest extends
		AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	protected RegistrationManager mRegistrationManager;
	@Autowired
	protected DataProcessor mDataProcessor;
	@Autowired
	protected UsersManager mUserManager;
	@Autowired
	protected RegistrationService mRegistrationService;

	@Test
	public void testSpringInit() {

		Assert.assertNotNull(mRegistrationManager);
		Assert.assertNotNull(mRegistrationService);
	}

	@Test(dependsOnMethods = "testSpringInit", dataProviderClass = RegistrationDataProvider.class, dataProvider = "getData")
	public void checkRegistration(String pInput) {

		ObjectMapper lMapper = new ObjectMapper();
		try {
			Object lTester = lMapper.readValue(pInput, Object.class);
			mRegistrationService.registrate(lTester, "fr");
			User lUser = mDataProcessor.retrieveObject(lTester, "user",
					User.class);
			String lEmail = lUser.getLogin();

			lUser = mUserManager.getUser(lEmail);
			Team lSavedTeam = mRegistrationManager.getUserTeam(lUser);
			Team lTeam = mDataProcessor.retrieveObject(lTester, "team",
					Team.class);

			Assert.assertNotNull(lSavedTeam);
			Assert.assertEquals(lTeam.getAddress(), lSavedTeam.getAddress());
			Assert.assertNotNull(lSavedTeam.getCrews());
			Assert.assertEquals(lSavedTeam.getCrews().size(), lTeam.getCrews()
					.size());

		} catch (Exception lE) {

		}
	}

}
