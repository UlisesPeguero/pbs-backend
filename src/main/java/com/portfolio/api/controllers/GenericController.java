package com.portfolio.api.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.portfolio.api.models.GenericEntity;
import com.portfolio.api.repositories.GenericRepository;
import com.portfolio.api.services.GenericService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class GenericController<T extends GenericEntity<T>> {

  protected final GenericService<T> service;

  protected GenericController(GenericRepository<T> repository) {
    this.service = new GenericService<T>(repository) {
    };
  }

  @GetMapping
  public ResponseEntity<List<T>> getAll(
      @RequestParam(required = false) String orderBy,
      @RequestParam(required = false) String order) {
    return ResponseEntity.ok(service.getAll(orderBy, order));
  }

  @GetMapping("page/{page}")
  public ResponseEntity<Page<T>> getPage(
      @PathVariable Integer page,
      @RequestParam(required = false) Integer rows,
      @RequestParam(required = false) String orderBy,
      @RequestParam(required = false) String order) {
    return ResponseEntity.ok(service.getAll(page, rows, orderBy, order));
  }

  @GetMapping("{id}")
  public ResponseEntity<T> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping
  public ResponseEntity<T> create(@RequestBody T newItem) {
    return ResponseEntity.ok(service.create(newItem));
  }

  @PutMapping("{id}")
  public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T updatedItem) {
    return ResponseEntity.ok(service.update(id, updatedItem));
  }

}