package com.bootcamp.services.product;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO LAB3-1.0 add bootstrap.yaml to refer to configuration server and
 * application name
 * 
 * Run the app and show that it is fetching configuration from configuration
 * server
 * 
 * <p>
 * 2020.0.0-M5 (Ilford) has significant changes in the way application is
 * configured. One of the important changes is that bootstrap is not enabled by
 * default. To enable bootstarp (or what they call legacy processing a starter
 * i.e. spring-cloud-starter-bootstrap must be added to pom. see
 * https://spring.io/blog/2020/10/07/spring-cloud-2020-0-0-m4-aka-ilford-is-available
 * for details.
 * 
 * Also explain that not all boot and cloud versions are compatible. A
 * compatibility list is maintained https://spring.io/projects/spring-cloud
 * 
 * @author amit
 *
 */
@SpringBootApplication
/*
 * TODO LAB4-2.0 add discovery starter and @EnableDiscoveryClient and show how
 * it registered with Eureka server.
 * 
 * TODO LAB4-2.2 add multiple instances of product service and show instance
 * updated in Eureka
 *
 */
@EnableDiscoveryClient
/*
 * TODO LAB8-1.3 enable Hysrtix
 */
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
/**
 * TODO LAB9-1.4 call via Feign
 */
@EnableFeignClients
@RestController
public class Lab3ProductServiceApplication {

	private Logger logger = LoggerFactory.getLogger(Lab3ProductServiceApplication.class);

	// TODO LAB3-2.1 inject config property
	// TODO LAB3-2.2 change fail-fast to false in bootstrap.yaml and show how app
	// falls
	// back to a local source

	@Value("${service.description}")
	private String serviceDescription;

	public static void main(String[] args) {
		SpringApplication.run(Lab3ProductServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return s -> {
			logger.info("Service description: {}", serviceDescription);
		};
	}
}
