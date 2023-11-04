package com.portfolio.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.portfolio.api.models.User;
import com.portfolio.api.models.dtos.UserGridViewDto;

@Repository
public interface UserRepository extends GenericRepository<User> {
  Optional<User> findByUsername(String username);

  List<UserGridViewDto> getGridView(Sort sort);

  Page<UserGridViewDto> getGridView(Pageable pageable);
}
