package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {
	
	private final EmployeeDao employeeDao;

	public EmployeeService(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	public Employee confimLogin(String username, String password) {
		Optional<Employee> employee = employeeDao
				.findAll()
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)) )
				.findFirst();
		return employee.isPresent() ? employee.get() : null;
	}
	
	public int insert(Employee employee) {
		return employeeDao.insert(employee);
	}
	
	public boolean update(Employee employee) {
		return employeeDao.update(employee);
	}

	public List<Employee> findAll() {
		return employeeDao.findAll();
	}
}
