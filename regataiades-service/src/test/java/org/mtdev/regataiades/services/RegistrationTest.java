package org.mtdev.regataiades.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mtdev.regataiades.business.interfaces.RegistrationManager;
import org.mtdev.regataiades.config.AppConfig;
import org.mtdev.regataiades.dao.interfaces.TeamDao;
import org.mtdev.regataiades.model.Athlete;
import org.mtdev.regataiades.model.Crew;
import org.mtdev.regataiades.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
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
public class RegistrationTest extends
		AbstractTransactionalTestNGSpringContextTests {

	private static String sInput = "{" + "\"totalPrice\":\"40 Euros\","
			+ "\"user\":{" + "\"login\":\"email@xxx.com\","
			+ "\"password\":\"pass\"," + "\"passwordConfirmation\":\"pass\""
			+ "}," + "\"team\":{" + "\"contactName\":\"Name\","
			+ "\"contactSurname\":\"surname\","
			+ "\"contactTelephone\":\"123456\"," + "\"name\":\"Club\","
			+ "\"address\":\"addr\"," + "\"zipCode\":\"12345\","
			+ "\"city\":\"City\"," + "\"country\":\"Country\","
			+ "\"coaches\":" + "[" + "{" + "\"$$hashKey\":" + "\"object:3\","
			+ "\"name\":\"a\"," + "\"surname\":\"b\"," + "\"sex\":\"M\""
			+ "}]," + "\"crews\":[" + "{"
			+ "\"$$hashKey\":\"object:5\",\"type\":\"M4x\"," + "\"members\":"
			+ "[{\"sex\":\"M\",\"$$hashKey\":\"object:11\"},"
			+ "{\"sex\":\"M\",\"$$hashKey\":\"object:12\"},"
			+ "{\"sex\":\"M\",\"$$hashKey\":\"object:13\"},"
			+ "{\"sex\":\"M\",\"$$hashKey\":\"object:14\"}]}]}}";

	@Autowired
	protected RegistrationManager mRegistrationManager;
	@Autowired
	protected RegistrationService mRegistrationService;

	@Autowired
	@Qualifier("teamDaoImpl")
	protected TeamDao mTeamDao;

	@Test
	public void testSpringInit() {

		Assert.assertNotNull(mRegistrationManager);
		Assert.assertNotNull(mRegistrationService);
	}

	@Test(dependsOnMethods = "testSpringInit")
	@Rollback(false)
	public void saveEmptyTeam() {
		Team lTeam = new Team();

		lTeam.setName("teamName");
		lTeam.setAddress("team Address");
		lTeam.setZipCode("team Zipcode");

		mTeamDao.create(lTeam);

	}

	@Test(dependsOnMethods = "saveEmptyTeam")
	public void checkSavedTeam() {
		Team lSavedTeam = mTeamDao.findById(1);

		Assert.assertNotNull(lSavedTeam);
		Assert.assertEquals("teamName", lSavedTeam.getName());
		Assert.assertEquals("team Address", lSavedTeam.getAddress());
		Assert.assertEquals("team Zipcode", lSavedTeam.getZipCode());
	}

	@Rollback(false)
	@Test(dependsOnMethods = "checkSavedTeam")
	public void saveFilledTeam() {

		Team lTeam = new Team();
		lTeam.setName("New team");

		Set<Crew> lCrews = new HashSet<Crew>();
		for (int j = 0; j < 10; j++) {
			Crew lCrew = new Crew();
			Set<Athlete> lAthletes = new HashSet<Athlete>();
			for (int i = 0; i < 4; i++) {
				Athlete lAthlete = new Athlete();
				lAthlete.setName("Ath" + i + "cr" + j);
				lAthletes.add(lAthlete);

			}
			lCrew.setAthletes(lAthletes);
			lCrew.setCategory("x"+j);
			lCrews.add(lCrew);
		}
		lTeam.setCrews(lCrews);

		mTeamDao.create(lTeam);
	}

	@Test(dependsOnMethods = "saveFilledTeam")
	public void checkSavedFilledTeam() {
		Team lTeam = mTeamDao.findById(2);
		Assert.assertNotNull(lTeam);

		Assert.assertNotNull(lTeam.getCrews());
		Assert.assertEquals(lTeam.getCrews().size(), 10);
	}

	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = "checkSavedFilledTeam")
	@Rollback(false)
	public void saveTeamFromJson() {
		ObjectMapper lMapper = new ObjectMapper();

		try {
			Object lTester = lMapper.readValue(sInput, Object.class);

			Team lTeam = lMapper.readValue(lMapper
					.writeValueAsString(((Map<Object, Object>) lTester)
							.get("team")), Team.class);
			mTeamDao.create(lTeam);

		} catch (IOException lE) {

		}
	}

	@Test(dependsOnMethods = "saveTeamFromJson")
	public void checkTeamSavedFromJson() {
		Team lTeam = mTeamDao.findById(3);
		Assert.assertNotNull(lTeam);

		Assert.assertNotNull(lTeam.getCrews());
		Assert.assertEquals(lTeam.getCrews().size(), 1);

		Assert.assertNotNull(lTeam.getCoaches());
		Assert.assertEquals(lTeam.getCoaches().size(), 1);

	}
}
