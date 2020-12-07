package com.bootcamp.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bootcamp.demo.controllers.dao.EmployeeRepository;
import com.bootcamp.demo.controllers.model.Employee;
import com.bootcamp.demo.controllers.model.MockEmployeeRepository;

/**
 * TODO LAB1-5.0 Add a method with return type of Object to demo json binding to
 * Thymeleaf template
 * 
 * @author amit
 *
 */
@Controller
@RequestMapping("/web/employees")
public class EmployeeWeb {
	Logger logger = LoggerFactory.getLogger(EmployeeWeb.class);

	// TODO LAB1-6.2 swith to JPA repository when ready
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public String defaultGreetingObjPayload(@PathVariable(name = "id") Integer id, Model model) {
		logger.info("Received id: {}", id);
		Employee employee = null;
		/**
		 * Uncomment this till task #5 when JPA repo is not yet introduced
		 */
		// employee = MockEmployeeRepository.employees.get(id);
		
		/**
		 * Uncomment this when JPA is enabled
		 */
		employee = employeeRepository.getOne(id); 
		
		logger.info("Found employee: {}", employee);
		model.addAttribute("employee", employee);
		return "index";
	}

}
