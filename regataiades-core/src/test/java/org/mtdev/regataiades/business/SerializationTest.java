package org.mtdev.regataiades.business;

import java.io.IOException;
import java.util.Map;

import org.mtdev.regataiades.model.Team;
import org.mtdev.regataiades.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationTest {

	private static String sInput = "{\"totalPrice\":\"40 Euros\",\"user\":{\"login\":\"email@xxx.com\",\"password\":\"pass\",\"passwordConfirmation\":\"pass\"},\"team\":{\"contactName\":\"Name\",\"contactSurname\":\"surname\",\"contactTelephone\":\"123456\",\"name\":\"Club\",\"address\":\"addr\",\"zipCode\":\"12345\",\"city\":\"City\",\"country\":\"Country\",\"coaches\":[{\"id\":4159,\"$$hashKey\":\"object:3\",\"name\":\"a\",\"surname\":\"b\",\"sex\":\"M\"}],\"crews\":[{\"id\":9349,\"$$hashKey\":\"object:5\",\"type\":\"M4x\",\"members\":[{\"sex\":\"M\",\"$$hashKey\":\"object:11\"},{\"sex\":\"M\",\"$$hashKey\":\"object:12\"},{\"sex\":\"M\",\"$$hashKey\":\"object:13\"},{\"sex\":\"M\",\"$$hashKey\":\"object:14\"}]}]}}";

	@SuppressWarnings("unchecked")
	@Test
	public void testTeam() {

		ObjectMapper lMapper = new ObjectMapper();

		try {
			Object lTester = lMapper.readValue(sInput, Object.class);
			Assert.assertNotNull(lTester);
			Assert.assertNotNull((((Map<Object, Object>) lTester).get("user")));
			User lUser = lMapper.readValue(lMapper
					.writeValueAsString(((Map<Object, Object>) lTester)
							.get("user")), User.class);
			Assert.assertNotNull(lUser);
			Assert.assertNotNull(lUser.getLogin());
			Assert.assertEquals(lUser.getLogin(), "email@xxx.com");
			Team lTeam = lMapper.readValue(lMapper
					.writeValueAsString(((Map<Object, Object>) lTester)
							.get("team")), Team.class);
			Assert.assertNotNull(lTeam);
			Assert.assertNotNull(lTeam.getContactSurname());
			Assert.assertEquals(lTeam.getContactTelephone(), "123456");

		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
