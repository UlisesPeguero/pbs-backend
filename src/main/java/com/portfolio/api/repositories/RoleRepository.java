package com.portfolio.api.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.portfolio.api.models.Role;

@Repository
public interface RoleRepository extends GenericRepository<Role> {
  Optional<Role> findByName(String name);
}
