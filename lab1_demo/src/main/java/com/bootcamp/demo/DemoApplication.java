package com.bootcamp.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author amit
 *
 *         TODO LAB1-1.0 change the server port to 8090. Explain how
 *         springboot automatically uses a "reasonable default" of 8080 Also add
 *         application name to the configuration
 */
@SpringBootApplication
public class DemoApplication {

	static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * TODO LAB1-2.0 add a command line runner and count number of
	 * beans/names auto-configured
	 * <p>
	 * Explain: Application Runner and Command Line Runner interfaces lets you to
	 * execute the code after the Spring Boot application is started. You can use
	 * these interfaces to perform any actions immediately after the application has
	 * started.
	 * </p>
	 * 
	 * <p>
	 * There is an alternative way to implement this by implementing
	 * CommandLineRunner interface and then run method.
	 * 
	 * Also explain the different between CommandLineRunner and ApplicationRunner
	 * </p>
	 */

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			// get count
			int beansCount = ctx.getBeanDefinitionCount();
			logger.info("There are {} beans automatically configured by SpringBoot", beansCount);
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			for (String beanName : beanNames) {
//				logger.info("Bean: {}", beanName);
//			}
		};
	}
}
