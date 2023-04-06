package com.portfolio.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.api.controllers.data.ListPayload;
import com.portfolio.api.models.AbstractEntity;
import com.portfolio.api.repositories.GenericRepository;
import com.portfolio.api.services.GenericService;

public abstract class GenericController<T extends AbstractEntity> {

  protected final GenericService<T> service;

  protected GenericController(GenericRepository<T> repository) {
    this.service = new GenericService<T>(repository) {
    };
  }

  @GetMapping
  public ResponseEntity<ListPayload<T>> getAll() {
    ListPayload<T> payload = new ListPayload<>();
    payload.setPayload(service.findAll());
    return ResponseEntity.ok(payload);
  }

}
