package com.portfolio.api.models.dtos;

import java.util.List;

public record UserInfoDto(
                Long id,
                String username,
                // List<RoleInfoDto> roles,
                EmployeeInfoDto employee) {

}
