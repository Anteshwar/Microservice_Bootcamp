package com.bootcamp.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.bootcamp.demo.controllers.model.Employee;
/**
 * TODO LAB1-6.4 show how certain methods can be disabled for some classes 
 * @author amit
 *
 */
@Configuration
public class NoDeleteRestConfigurer implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
		ExposureConfiguration config = restConfig.getExposureConfiguration();
		config.forDomainType(Employee.class)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.DELETE));
	}
}
