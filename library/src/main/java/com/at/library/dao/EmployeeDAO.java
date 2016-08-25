package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Employee;

public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

}
