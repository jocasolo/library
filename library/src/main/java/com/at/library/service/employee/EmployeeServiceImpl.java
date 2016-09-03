package com.at.library.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.EmployeeDAO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;
import com.at.library.service.CommonService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao;

	@Autowired
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDTO> findAll(Pageable pageable) {
		final List<Employee> employees = employeeDao.findAll(pageable);
		return commonService.transform(employees, EmployeeDTO.class);
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
	public EmployeeDTO create(EmployeeDTO employeeDto) {
		final Employee employee = commonService.transform(employeeDto, Employee.class);
		employeeDao.save(employee);
		return commonService.transform(employee, EmployeeDTO.class);
	}

}
