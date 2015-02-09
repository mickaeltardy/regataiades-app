package org.mtdev.regataiades.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestSessionFactoryBean extends LocalSessionFactoryBean {

	@Autowired
	protected DataSource mDataSource;

	public TestSessionFactoryBean() {

		this.setPackagesToScan(getPackagesToScan());
		this.setHibernateProperties(getHibernateProperties());

	}

	@Autowired
	public void setDataSource(DataSource pDataSource) {
		// mDataSource = pDataSource;
		super.setDataSource(pDataSource);
	}

	public String[] getPackagesToScan() {
		List<String> lPackages = new ArrayList<String>();
		lPackages.add("org.mtdev.regataiades.model");

		return lPackages.toArray(new String[0]);
	}

	@Override
	public Properties getHibernateProperties() {

		Properties lProps = new Properties();
		lProps.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		/*
		 * lProps.put("hibernate.connection.url",
		 * "jdbc:mysql://localhost:3306/regataiades");
		 * lProps.put("hibernate.connection.username", "root");
		 * lProps.put("hibernate.connection.password", "");
		 */
		lProps.put("hibernate.show_sql", "true");
		lProps.put("hibernate.hbm2ddl.auto", "create");
		/* lProps.put("hibernate.connection.autocommit", "false"); */
		/*
		 * lProps.put("hibernate.transaction.flush_before_completion", "false");
		 * lProps.put("hibernate.transaction.factory_class",
		 * "org.hibernate.transaction.JDBCTransactionFactory");
		 */
		/* lProps.put("hibernate.current_session_context_class", "thread"); */
		return lProps;
	}
}
