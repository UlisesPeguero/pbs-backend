package com.portfolio.api.controllers;

import com.portfolio.api.models.Employee;
import com.portfolio.api.repositories.EmployeeRepository;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@PreAuthorize("hasAuthority('EMPLOYEES')")
public class EmployeeController extends GenericController<Employee> {
    protected EmployeeController(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }
}