package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {
	
	public int insert(Employee employee) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.getTransaction();
		
		int pk = (int) session.save(employee);
		
		transaction.commit();
		
		return pk;
	}
	
	public boolean update(Employee employee) {
		return false;
	}
	
	public boolean delete(Employee employee) {
		return false;
	}
	
	public List<Employee> findAll() {
		Session session = HibernateUtil.getSession();
		
		List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
		return employees;
	}
}
