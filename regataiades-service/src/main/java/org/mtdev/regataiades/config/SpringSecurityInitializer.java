package org.mtdev.regataiades.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.mtdev.regataiades.services.SimpleCORSFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
 
public class SpringSecurityInitializer
extends AbstractSecurityWebApplicationInitializer 
{

	
	

	@Override
	protected void beforeSpringSecurityFilterChain(
			ServletContext pServletContext) {
		FilterRegistration.Dynamic corsFilter = pServletContext.addFilter("corsFilter", new SimpleCORSFilter());	
        corsFilter.addMappingForUrlPatterns(null, true, "/*");
		super.beforeSpringSecurityFilterChain(pServletContext);
		

		
	}
	
	
	
   //do nothing
}