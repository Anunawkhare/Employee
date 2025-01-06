package com.jpa.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.crud.model.Employee;
import com.jpa.crud.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee saveOrUpdate(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(int empId) {
		return employeeRepository.findById(empId).get();
	}

	public List<Employee> getAllEmployee() {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
		return employeeList;
	}

	public void delete(int id) {
		employeeRepository.deleteById(id);
	}

}