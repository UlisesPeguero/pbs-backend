package com.portfolio.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portfolio.api.models.User;

@Repository
public interface UserRepository extends GenericRepository<User> {
  Optional<User> findByUsername(String username);

}
