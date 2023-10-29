package com.portfolio.api.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.api.models.User;
import com.portfolio.api.models.dtos.UserGridViewDto;
import com.portfolio.api.models.dtos.UserInfoDto;

@Repository
public interface UserRepository extends GenericRepository<User> {
  Optional<User> findByUsername(String username);

  @Query("""
            SELECT new com.portfolio.api.models.dtos.UserInfoDto(
              u.id,
              u.username,
              new com.portfolio.api.models.dtos.EmployeeInfoDto(
                u.employee.id,
                u.employee.lastName,
                u.employee.position
              )
            )
            FROM #{#entityName}  u
      """)
  <T> List<T> getGridView2(Sort sort, Class<T> type);

  List<UserGridViewDto> getGridView(Sort sort);
}
