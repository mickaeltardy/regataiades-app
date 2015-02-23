package org.mtdev.regataiades.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.mtdev.regataiades.services.SimpleCORSFilter;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		super.onStartup(servletContext);
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
				"encoding-filter", new SimpleCORSFilter());
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");

	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}

	@Override
	protected Filter[] getServletFilters() {

		SimpleCORSFilter lSimpleCORSFilter = new SimpleCORSFilter();

		return new Filter[] { lSimpleCORSFilter };

	}

	protected WebApplicationContext createRootApplicationContext() {
		System.out.println("Setting up active profile");
		WebApplicationContext context = (WebApplicationContext) super
				.createRootApplicationContext();

		((ConfigurableEnvironment) context.getEnvironment())
				.setActiveProfiles("dev");

		return context;
	}

}