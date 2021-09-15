package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	private static final Logger LOG = Logger.getLogger(RequestHelper.class);
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final EmployeeService EMPLOYEE_SERVICE = new EmployeeService(new EmployeeDao());

	public static void processEmployee(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		final List<Employee> employees = EMPLOYEE_SERVICE.findAll();
		
		final String json = OBJECT_MAPPER.writeValueAsString(employees);
		final PrintWriter out = response.getWriter();
		out.println(json);
	}
	
	public static void processLogin(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
		final BufferedReader reader = request.getReader();
		final StringBuilder builder = new StringBuilder();
		
		String line = reader.readLine();
		while(line != null) {
			builder.append(line);
			line = reader.readLine();
		}
		
		final String[] split = builder.toString().split("[=&]");
		
		final String username = split[1];
		final String password = split[3];
		
		final Employee employee = EMPLOYEE_SERVICE.confimLogin(username, password);
		if(employee != null) {
			final HttpSession session = request.getSession();
			session.setAttribute("User", employee);
			
			response.setContentType("text/html");
			response.getWriter().println(OBJECT_MAPPER.writeValueAsString(employee));
			
			LOG.info("User " + username + " successfully logged in.");
		} else {
			response.setStatus(204);
			LOG.warn("User " + username + " failed to log in.");
		}
	}
	
	public static void processError(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("error.html").forward(request, response);
	}
	
}
