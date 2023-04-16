package com.portfolio.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.portfolio.api.models.GenericEntity;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T>> extends JpaRepository<T, Long> {
  public List<T> findByActive(Boolean active);

  public List<T> findByActive(Boolean active, Pageable pageable);
}
