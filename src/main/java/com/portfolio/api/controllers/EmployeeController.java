package com.portfolio.api.controllers;

import com.portfolio.api.models.Employee;
import com.portfolio.api.repositories.EmployeeRepository;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RolesAllowed("EMPLOYEES")
public class EmployeeController extends GenericController<Employee> {
    protected EmployeeController(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }
}