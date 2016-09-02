package com.at.library.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.EmployeeDAO;
import com.at.library.dto.DTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDTO> findAll(Pageable pageable) {
		final List<Employee> employees = employeeDao.findAll(pageable);
		return transform(employees, EmployeeDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findOne(Integer id) throws EmployeeNotFoundException {
		final Employee employee = employeeDao.findOne(id);
		if (employee == null)
			throw new EmployeeNotFoundException();
		return employee;
	}

	@Override
	public void update(EmployeeDTO employeeDto) {
		Employee employee = transform(employeeDto);
		employeeDao.save(employee);
	}

	@Override
	public EmployeeDTO create(EmployeeDTO employeeDto) {
		final Employee employee = transform(employeeDto);
		employeeDao.save(employee);
		return transform(employee, EmployeeDTO.class);
	}

	@Override
	public void delete(Integer id) {
		employeeDao.delete(id);
	}

	@Override
	public Employee transform(DTO employeeDto) {
		return dozer.map(employeeDto, Employee.class);
	}

	@Override
	public <T> T transform(Employee employee, Class<T> destinationClass) {
		return dozer.map(employee, destinationClass);
	}

	@Override
	public <T> List<T> transform(List<Employee> employees, Class<T> destinationClass) {
		List<T> res = new ArrayList<>();
		for (Employee employee : employees)
			res.add(dozer.map(employee, destinationClass));
		return res;
	}

}
