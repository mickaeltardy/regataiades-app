package org.mtdev.regataiades.services;

import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.mtdev.regataiades.dao.interfaces.EntityDao;
import org.mtdev.regataiades.dao.interfaces.TeamDao;
import org.mtdev.regataiades.dao.interfaces.UserDao;
import org.mtdev.regataiades.model.Athlete;
import org.mtdev.regataiades.model.Coach;
import org.mtdev.regataiades.model.Crew;
import org.mtdev.regataiades.security.SecurityConfig;
import org.mtdev.regataiades.security.StatelessAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@Import({ StatelessAuthenticationSecurityConfig.class })
@ComponentScan(basePackages = "org.mtdev.regataiades.*", excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Repository.class),
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Profile("wartest")
public class AppTestConfig {
	@Autowired
	private SessionFactory mSessionFactory;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new HibernateTransactionManager(mSessionFactory);
	}

	@Bean
	public UserDao userDao() {
		UserDao lUserDao = Mockito.mock(UserDao.class);

		return lUserDao;
	}

	@Bean
	public TeamDao teamDaoImpl() {
		return Mockito.mock(TeamDao.class);
	}

	@SuppressWarnings("unchecked")
	@Bean
	public EntityDao<Athlete> athleteDaoImpl() {
		return Mockito.mock(EntityDao.class);
	}

	@SuppressWarnings("unchecked")
	@Bean
	public EntityDao<Crew> crewDaoImpl() {
		return Mockito.mock(EntityDao.class);
	}

	@SuppressWarnings("unchecked")
	@Bean
	public EntityDao<Coach> coachDaoImpl() {
		return Mockito.mock(EntityDao.class);
	}
}
