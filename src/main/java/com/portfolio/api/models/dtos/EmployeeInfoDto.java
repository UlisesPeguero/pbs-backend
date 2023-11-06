package com.portfolio.api.models.dtos;

import com.portfolio.api.models.Position;

public record EmployeeInfoDto(
    Long id,
    String fullName,
    Position position) {

}
