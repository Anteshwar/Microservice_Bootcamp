package com.bootcamp.services.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * TODO LAB5-1.0 setup the service, controller, configuration etc.
 * enable eureka client
 * @author amit
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Lab5DiscountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab5DiscountServiceApplication.class, args);
	}

}
