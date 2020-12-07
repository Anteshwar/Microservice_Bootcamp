package com.bootcamp.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * TODO LAB2-1.0 add local and git props and add {@link EnableConfigServer}
 * Load config in browser from http://localhost:8091/application/default & other profile
 * @author amit
 *
 */
@SpringBootApplication
@EnableConfigServer
public class Lab2ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab2ConfigServerApplication.class, args);
	}

}
