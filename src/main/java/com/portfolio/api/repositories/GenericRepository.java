package com.portfolio.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.portfolio.api.models.AbstractEntity;

@NoRepositoryBean
public interface GenericRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
  public List<T> findByActive(Boolean active);

  public List<T> findByActive(Boolean active, Pageable pageable);
}
