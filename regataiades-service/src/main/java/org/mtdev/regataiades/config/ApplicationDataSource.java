package org.mtdev.regataiades.config;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ApplicationDataSource extends DriverManagerDataSource {

	public ApplicationDataSource() {
		super("jdbc:mysql://localhost" + ":3306/regataiades"
				+ "?zeroDateTimeBehavior=convertToNull", "root", "");
	}

}
