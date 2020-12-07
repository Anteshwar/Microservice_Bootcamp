package com.bootcamp.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.demo.controllers.model.Employee;
import com.bootcamp.demo.controllers.model.MockEmployeeRepository;

/**
 * 
 * @author amit
 *
 *         TODO LAB1-4.0 create a model object to keep employee data.
 * 
 *         Add a rest controller and provide a method to return json with
 *         employee details. Explain meaning of content types, auto conversions
 *         by spring boot etc.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	/**
	 * TODO LAB1-4.1 add a method to return only name as a json
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/{id}/basic", method = RequestMethod.GET)
	public Map<String, String> getEmployeeBasic(@PathVariable Integer id) {
		logger.info("Received id: {}", id);
		Map<String, String> responseMap = new HashMap<>();
		Employee employee = MockEmployeeRepository.employees.get(id);
		logger.info("Found employee: {}", employee);
		if (employee != null) {
			responseMap.put("firstName", employee.getFirstName());
			responseMap.put("lastName", employee.getLastName());
		}
		return responseMap;
	}

	/**
	 * TODO LAB1-4.2 add a method to return entire employee object
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable Integer id) {
		logger.info("Received id: {}", id);
		Employee employee = MockEmployeeRepository.employees.get(id);
		logger.info("Found employee: {}", employee);
		return employee;
	}

}
