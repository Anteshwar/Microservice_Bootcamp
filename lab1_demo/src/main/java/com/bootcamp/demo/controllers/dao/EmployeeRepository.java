package com.bootcamp.demo.controllers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.demo.controllers.model.Employee;

/**
 * TODO LAB1-6.1 add data repository for JPA
 * TODO LAB1-6.3 uncomment data rest to demo data rest capability (although data rest may work without it!)
 * @author amit
 *
 */
@Repository
// @RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
