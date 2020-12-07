package com.bootcamp.auditor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LABC-1.1 add following starters to pom 
 * 
 * <code>
 * 	Eureka Client
 * 	Config Client
 *  Actuator
 *  Web
 *  Cloud Stream
 *  RabitMQ
 *  JPA
 *  Dev Tools
 *  Thymeleaf
 *  Sleuth/Zipkin
 * </code>
 * 
 * @author amit
 *
 */
@SpringBootApplication
public class Lab7WebAuditAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab7WebAuditAppApplication.class, args);
	}

}
