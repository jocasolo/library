package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.service.employee.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<EmployeeDTO> getAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug("Buscando todos los empleados en el sistema.");
		return employeeService.findAll(new PageRequest(page, size));
	}

	@RequestMapping(method = { RequestMethod.POST })
	public EmployeeDTO create(
			@RequestBody EmployeeDTO employee) {
		log.debug(String.format("Creando el empleado: %s", employee));
		return employeeService.create(employee);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public EmployeeDTO findOne(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		log.debug(String.format("Buscando el empleado con id: %s", id));
		return employeeService.transform(employeeService.findOne(id), EmployeeDTO.class);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(
			@PathVariable("id") Integer id, 
			@RequestBody EmployeeDTO employeeDTO) {
		throw new UnsupportedOperationException();
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		throw new UnsupportedOperationException();
	}
}
