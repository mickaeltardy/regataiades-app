package org.mtdev.regataiades.services;

import org.testng.annotations.DataProvider;

public class RegistrationDataProvider {

	@DataProvider
	public static Object[][] getData() {

		return new Object[][] { { "{" + "\"totalPrice\":\"40 Euros\","
				+ "\"user\":{" + "\"login\":\"email@xxx.com\","
				+ "\"password\":\"pass\","
				+ "\"passwordConfirmation\":\"pass\"" + "}," + "\"team\":{"
				+ "\"contactName\":\"Name\","
				+ "\"contactSurname\":\"surname\","
				+ "\"contactTelephone\":\"123456\"," + "\"name\":\"Club\","
				+ "\"address\":\"addr\"," + "\"zipCode\":\"12345\","
				+ "\"city\":\"City\"," + "\"country\":\"Country\","
				+ "\"coaches\":" + "[" + "{" + "\"$$hashKey\":"
				+ "\"object:3\"," + "\"name\":\"a\"," + "\"surname\":\"b\","
				+ "\"sex\":\"M\"" + "}]," + "\"crews\":[" + "{"
				+ "\"$$hashKey\":\"object:5\",\"type\":\"M4x\","
				+ "\"members\":"
				+ "[{\"sex\":\"M\",\"$$hashKey\":\"object:11\"},"
				+ "{\"sex\":\"M\",\"$$hashKey\":\"object:12\"},"
				+ "{\"sex\":\"M\",\"$$hashKey\":\"object:13\"},"
				+ "{\"sex\":\"M\",\"$$hashKey\":\"object:14\"}]}]}}" } };
	};
}
