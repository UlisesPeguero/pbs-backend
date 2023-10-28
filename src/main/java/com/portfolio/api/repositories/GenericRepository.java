package com.portfolio.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import com.portfolio.api.models.GenericEntity;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T>> extends JpaRepository<T, Long> {

  public List<T> findByActive(Boolean active);

  public List<T> findByActive(Boolean active, Sort sort);

  public List<T> findByActive(Boolean active, Pageable pageable);

  public <S> List<S> findByActive(Boolean active, Sort sort, Class<S> type);

  @Query("select e from #{#entityName} e")
  public <S> List<S> findAll(Sort sort, Class<S> type);
}
