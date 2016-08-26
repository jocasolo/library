package com.at.library.service.employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.EmployeeDAO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<EmployeeDTO> findAll() {
		final Iterator<Employee> iterator = employeeDao.findAll().iterator();
		final List<EmployeeDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Employee r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public Employee findOne(Integer id) {
		final Employee employee = employeeDao.findOne(id);
		return employee;
	}

	@Override
	public EmployeeDTO create(EmployeeDTO employeeDto) {
		final Employee employee = transform(employeeDto);
		return transform(employeeDao.save(employee));
	}

	@Override
	public void update(EmployeeDTO employeeDto) {
		Employee employee = transform(employeeDto);
		employeeDao.save(employee);
	}

	@Override
	public void delete(Integer id) {
		employeeDao.delete(id);
	}

	@Override
	public EmployeeDTO transform(Employee employee) {
		return dozer.map(employee, EmployeeDTO.class);
	}

	@Override
	public <T> Employee transform(T employeeDto) {
		return dozer.map(employeeDto, Employee.class);
	}

}
