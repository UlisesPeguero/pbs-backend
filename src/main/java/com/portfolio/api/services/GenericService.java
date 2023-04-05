package com.portfolio.api.services;

import java.util.List;

import com.portfolio.api.models.AbstractEntity;
import com.portfolio.api.repositories.GenericRepository;

public abstract class GenericService<T extends AbstractEntity> {

  protected GenericRepository<T> repository;

  protected GenericService(GenericRepository<T> repository) {
    this.repository = repository;
  }
  
  public List<T> findAll() {
    return repository.findAll();
  }

  public List<T> findAll(boolean active) {
    return repository.findByActive(active);
  }

}
