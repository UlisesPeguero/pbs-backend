package com.portfolio.api.models.dtos;

import com.portfolio.api.models.Employee;

public interface UserGridViewDto {
  Long getId();

  String getUsername();

  EmployeeGridViewDto getEmployee();
}
