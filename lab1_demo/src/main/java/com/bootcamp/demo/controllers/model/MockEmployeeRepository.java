package com.bootcamp.demo.controllers.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.Repository;

/**
 * TODO LAB1-5.1 add a repo to hold data till Spring Data {@link Repository} is developed
 * 
 * @author amit
 *
 */
public class MockEmployeeRepository {
	public static Map<Integer, Employee> employees = new HashMap<>();
	// mark that it is bad practice

	static {
		employees.put(1, new Employee(1, "Amit", "Kumar", "Microservice Deveopment", 2));
		employees.put(2, new Employee(2, "Neha", "Sinha", "Cloud Deveopment", 5));
	}
}
