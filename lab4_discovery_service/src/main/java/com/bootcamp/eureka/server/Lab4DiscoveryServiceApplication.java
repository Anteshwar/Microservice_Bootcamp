package com.bootcamp.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * TODO LAB4-1.0 add property to add port and disable discovery client functions
 * @author amit
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class Lab4DiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab4DiscoveryServiceApplication.class, args);
	}

}
