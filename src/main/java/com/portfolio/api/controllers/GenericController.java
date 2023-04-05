package com.portfolio.api.controllers;

import com.portfolio.api.models.AbstractEntity;
import com.portfolio.api.repositories.GenericRepository;
import com.portfolio.api.services.GenericService;

public abstract class GenericController<T extends AbstractEntity> {

  protected final GenericService<T> service;

  protected GenericController(GenericRepository<T> repository) {
    this.service = new GenericService<T>(repository) {
    };
  }
  
}
