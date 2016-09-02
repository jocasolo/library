package com.at.library.service.employee;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.DTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

public interface EmployeeService {

	/**
	 * Realiza la busqueda de todos los empleados existentes.
	 * 
	 * @return Listado de empleados.
	 */
	List<EmployeeDTO> findAll(Pageable pageable);

	/**
	 * Realiza la búsqueda de un empleado por id.
	 * 
	 * @param id
	 * @return Empleado correspondiente al id buscado.
	 * @throws EmployeeNotFoundException 
	 */
	Employee findOne(Integer id) throws EmployeeNotFoundException;

	/**
	 * Crea un nuevo empleado.
	 * 
	 * @param employeeDTO
	 * @return El empleado creado.
	 */
	EmployeeDTO create(EmployeeDTO employeeDto);

	/**
	 * Actualiza un empleado.
	 * 
	 * @param employee
	 */
	void update(EmployeeDTO employeeDto);

	/**
	 * Elimina el empleado correspondiente al id dado.
	 * 
	 * @param id
	 */
	void delete(Integer id);
	
	Employee transform(DTO employeeDto);

	<T> T transform(Employee employee, Class<T> destinationClass);

	<T> List<T> transform(List<Employee> employees, Class<T> destinationClass);
}
