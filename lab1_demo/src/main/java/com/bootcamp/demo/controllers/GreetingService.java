package com.bootcamp.demo.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author amit
 *
 *         TODO LAB1-3.0 add a rest controller and provide a method to return
 *         greeting. Explain annotations like {@link RestController} and
 *         {@link RequestMapping}
 * 
 *         Also explain that SpringBoot by default scans the packages under the
 *         package where {@link SpringBootApplication} is defined. It can be
 *         overridden using {@link ComponentScan} annotation
 */
@RestController
@RequestMapping("/greetings")
public class GreetingService {

	@RequestMapping(path = "/greet", method = RequestMethod.GET)
	public String greet(@RequestParam(name = "name", required = false) String name) {
		if (StringUtils.isEmpty(name)) {
			return "Hello Anonymous!";
		} else {
			return "Hello " + name + "!";
		}
	}
}
