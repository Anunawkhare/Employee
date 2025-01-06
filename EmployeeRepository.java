package com.jpa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.crud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>  
{  
}  