package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Employee;

public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

	@Query(value = "SELECT e from Employee AS e")
	public List<Employee> findAll(Pageable pageable);
	
}
