package com.bootcamp.demo.controllers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * TODO LAB1-6.0 add annotation to make it an entity
 * @author amit
 *
 */
@Entity(name = "EMPLOYEES")
public class Employee {
	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String department;
	private Integer experiance;

	public Employee() {
		super();
	}

	public Employee(Integer empoyeeId, String firstName, String lastName, String department, Integer experiance) {
		super();
		this.employeeId = empoyeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.experiance = experiance;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer empoyeeId) {
		this.employeeId = empoyeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getExperiance() {
		return experiance;
	}

	public void setExperiance(Integer experiance) {
		this.experiance = experiance;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", department=" + department + ", experiance=" + experiance + "]";
	}

}
