package com.jpa.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpa.crud.model.Employee;
import com.jpa.crud.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;

	private Employee employee;

	@BeforeEach
	public void setUp() {
		employee = new Employee();
		employee.setEmpId(1);
		employee.setEmpName("John Doe");
		employee.setDepartment("IT");
		employee.setSalary(50000);
	}

	@Test
	public void testSaveOrUpdateEmployee() {
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		Employee savedEmployee = employeeService.saveOrUpdate(employee);

		assertEquals(employee, savedEmployee);
		verify(employeeRepository).save(employee);
	}

	@Test
	public void testGetEmployeeById() {
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

		Employee retrievedEmployee = employeeService.getEmployeeById(1);

		assertEquals(employee, retrievedEmployee);
		verify(employeeRepository).findById(1);
	}

	@Test
	public void testGetAllEmployee() {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);

		when(employeeRepository.findAll()).thenReturn(employeeList);

		List<Employee> retrievedEmployeeList = employeeService.getAllEmployee();

		assertEquals(employeeList.size(), retrievedEmployeeList.size());
		assertEquals(employeeList.get(0), retrievedEmployeeList.get(0));
		verify(employeeRepository).findAll();
	}

	@Test
	public void testDeleteEmployee() {
		employeeService.delete(1);

		verify(employeeRepository).deleteById(1);
	}
}
