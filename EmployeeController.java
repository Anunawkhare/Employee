package com.jpa.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.crud.model.Employee;
import com.jpa.crud.service.EmployeeService;

@RequestMapping("/api")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	private ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		try {
			employeeService.saveOrUpdate(employee);
			return new ResponseEntity<>(employee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/employee/{empId}")
	private ResponseEntity<Employee> updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee) {

		Employee _employee = employeeService.getEmployeeById(empId);

		if (_employee != null) {
			return new ResponseEntity<Employee>(employeeService.saveOrUpdate(employee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/employee/{empId}")
	private ResponseEntity<Employee> getEmployee(@PathVariable("empId") int empId) {
		Employee employee = employeeService.getEmployeeById(empId);
		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/employeeList")
	private ResponseEntity<List<Employee>> getAllEmployee() {
		try {
			List<Employee> employeeList = employeeService.getAllEmployee();
			if (employeeList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employee/{empId}")
	private ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("empId") int empId) {

		try {
			employeeService.delete(empId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}