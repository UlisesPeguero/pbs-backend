package com.portfolio.api.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
  public ResponseEntity<List<T>> getAll(
      @RequestParam(required = false) String orderBy,
      @RequestParam(required = false) String order) {
    return ResponseEntity.ok(service.findAll(orderBy, order));
  }

  @GetMapping("page/{page}")
  public ResponseEntity<Page<T>> getPage(
      @PathVariable Integer page,
      @RequestParam(required = false) Integer rows,
      @RequestParam(required = false) String orderBy,
      @RequestParam(required = false) String order) {
    return ResponseEntity.ok(service.findAll(page, rows, orderBy, order));
  }

}