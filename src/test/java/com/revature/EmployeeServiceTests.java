package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {
	
	private EmployeeDao mockdao;
	private EmployeeService employeeService;
	
	@Before
	public void setup() {
		mockdao = mock(EmployeeDao.class);
		employeeService = new EmployeeService(mockdao);
	}
	
	@After
	public void teardwon() {
		mockdao = null;
		employeeService = null;
	}
	
	@Test
	public void testConfirmLogin_success() {
		List<Employee> employees = Arrays.asList( new Employee[] {
				new Employee(3, "Scott","Lang","Antman","bugs"),
				
			});
		Employee testEmployee = new Employee(43, "Clint","Barton","Hawkeye","arrows");
		employees.add(testEmployee);
		
		when(mockdao.findAll()).thenReturn(employees);
		
		assertEquals(employeeService.confimLogin("Hawkeye", "arrows"),testEmployee);
	}
	
	@Test
	public void testFailConfirmLogin_returnNull() {
		List<Employee> employees = Arrays.asList( new Employee[] {
				new Employee(3, "Scott","Lang","Antman","bugs"),
				new Employee(43, "Clint","Barton","Hawkeye","arrows")
			});
		
		when(mockdao.findAll()).thenReturn(employees);
		
		assertNull(employeeService.confimLogin("Hawkeye", "not-arrows"));
	}
	
	
}
