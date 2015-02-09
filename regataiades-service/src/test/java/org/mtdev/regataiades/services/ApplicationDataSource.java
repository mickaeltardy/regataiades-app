package org.mtdev.regataiades.services;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class ApplicationDataSource extends DriverManagerDataSource {

	public ApplicationDataSource() {
		super("jdbc:mysql://localhost" + ":3306/regataiades_test"
				+ "?zeroDateTimeBehavior=convertToNull", "root", "");
	}

}
