package org.mtdev.regataiades.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDataSource extends DriverManagerDataSource {

	public ApplicationDataSource() {
		super("jdbc:mysql://localhost" + ":3306/regataiades"
				+ "?zeroDateTimeBehavior=convertToNull", "root", "");
	}

}
