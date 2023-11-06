package com.portfolio.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.api.models.User;
import com.portfolio.api.models.dtos.UserGridViewDto;

@Repository
public interface UserRepository extends GenericRepository<User> {
  Optional<User> findByUsername(String username);

  List<UserGridViewDto> getGridView(Sort sort);

  Page<UserGridViewDto> getGridView(Pageable pageable);

  @Query("SELECT u FROM User u WHERE u.username LIKE %:searchBy% OR u.employee.firstName LIKE %:searchBy% OR u.employee.lastName LIKE %:searchBy%")
  List<UserGridViewDto> search(@Param("searchBy") String searchBy, Sort sort);

  @Query("SELECT u FROM User u WHERE u.username LIKE %:searchBy% OR u.employee.firstName LIKE %:searchBy% OR u.employee.lastName LIKE %:searchBy%")
  Page<UserGridViewDto> search(@Param("searchBy") String searchBy, Pageable pageable);
}
