package org.mtdev.regataiades.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mtdev.regataiades.business.interfaces.NotificationManager;
import org.mtdev.regataiades.config.AppConfig;
import org.mtdev.regataiades.model.Crew;
import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
@ActiveProfiles("test")
public class NotificationTest extends
		AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	protected NotificationManager mNotificationManager;

	@Test
	public void testAccountCreationNotification() {
		mNotificationManager.setLanguage("fr");
		User lUser = new User();

		lUser.setLogin("mishgunn@gmail.com");
		lUser.setPassword("xxxxx");

		Team lTeam = new Team();

		lTeam.setInvited(false);

		mNotificationManager.notifyAccountCreation(lUser, lTeam);
		mNotificationManager.setLanguage("en");
		mNotificationManager.notifyAccountCreation(lUser, lTeam);

	}

	@Test
	public void testFirstRegistrationNotification() {
		mNotificationManager.setLanguage("fr");
		Team lTeam = new Team();

		lTeam.setContactEmail("mishgunn@gmail.com");
		lTeam.setName("UNA");
		lTeam.setContactName("Mike");
		lTeam.setContactSurname("Tardy");

		lTeam.setInvited(false);
		Set<Crew> lCrews = new HashSet<Crew>();
		Crew lCrew1 = new Crew();
		lCrew1.setCategory("HU4x");
		lCrew1.setId(1);
		lCrews.add(lCrew1);
		Crew lCrew2 = new Crew();
		lCrew2.setCategory("HU4x");
		lCrew2.setId(2);
		lCrews.add(lCrew2);
		Crew lCrew3 = new Crew();
		lCrew3.setCategory("HU8+");
		lCrew3.setId(3);
		lCrews.add(lCrew3);

		lTeam.setCrews(lCrews);

		lTeam.setAthletesNum(16);

		mNotificationManager.notifyFirstRegistration(lTeam);
		mNotificationManager.setLanguage("en");
		mNotificationManager.notifyFirstRegistration(lTeam);
		
		

	}

	
	
}
