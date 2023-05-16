package com.portfolio.api.controllers;

import com.portfolio.api.models.Employee;
import com.portfolio.api.repositories.EmployeeRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends GenericController<Employee> {
    public EmployeeController(EmployeeRepository employeeRepository) {
        super(employeeRepository, "EMPLOYEES");
    }
}