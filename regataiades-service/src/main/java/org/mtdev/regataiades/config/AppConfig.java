package org.mtdev.regataiades.config;

import org.hibernate.SessionFactory;
import org.mtdev.regataiades.security.StatelessAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan({ "org.mtdev.regataiades.*" })
@Import({ StatelessAuthenticationSecurityConfig.class })
@ImportResource("classpath:/annotation-driven-conf.xml")
public class AppConfig {
	@Autowired
	private SessionFactory mSessionFactory;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new HibernateTransactionManager(mSessionFactory);
	}
}