package com.at.library.service.employee;

import java.util.List;

import com.at.library.dto.EmployeeDTO;
import com.at.library.model.Employee;

public interface EmployeeService {

	/**
	 * Realiza la busqueda de todos los empleados existentes.
	 * 
	 * @return Listado de empleados.
	 */
	List<EmployeeDTO> findAll();

	/**
	 * Realiza la búsqueda de un empleado por id.
	 * 
	 * @param id
	 * @return Empleado correspondiente al id buscado.
	 */
	EmployeeDTO findOne(Integer id);

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

	/**
	 * Transforma un Employee en un EmployeeDTO
	 * 
	 * @param employee
	 * @return
	 */
	EmployeeDTO transform(Employee employee);

	/**
	 * Transforma un DTO en un objeto Employee. 
	 * @param employee
	 * @return
	 */
	<T> Employee transform(T employeeDto);
}
