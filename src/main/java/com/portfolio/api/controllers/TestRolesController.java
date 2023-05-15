package com.portfolio.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api.models.Employee;
import com.portfolio.api.repositories.EmployeeRepository;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/tests")
// @PreAuthorize("hasAuthority('EMPLOYEES')")
@RolesAllowed("ADMIN")
public class TestRolesController extends GenericController<Employee> {

  public TestRolesController(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }

  @GetMapping("global")
  public String globalRoleTest() {
    return "It works";
  }

  @GetMapping("other")
  // @PreAuthorize("hasAuthority('ADMIN')")
  @RolesAllowed("USER")
  public String otherRole() {
    return "Works with other role";
  }

}
