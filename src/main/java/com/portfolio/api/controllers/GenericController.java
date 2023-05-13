package com.portfolio.api.controllers;

import java.lang.module.ResolutionException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.portfolio.api.controllers.payloads.MessageResponse;
import com.portfolio.api.exceptions.FailedValidationException;
import com.portfolio.api.exceptions.ResourceNotFoundException;
import com.portfolio.api.models.GenericEntity;
import com.portfolio.api.repositories.GenericRepository;
import com.portfolio.api.services.GenericService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class GenericController<T extends GenericEntity<T>> {

  protected final GenericService<T> service;
  protected String mainRole = "ADMIN";

  protected GenericController(GenericRepository<T> repository) {
    this.service = new GenericService<T>(repository) {
    };
  }

  protected GenericController(GenericRepository<T> repository, String mainRole) {
    this(repository);
    this.mainRole = mainRole;
  }

  protected GenericController(GenericService<T> service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<T>> getAll(
      @RequestParam(required = false) String orderBy,
      @RequestParam(required = false) String order,
      HttpServletRequest request) {
    if (!request.isUserInRole(mainRole)) {
      throw new ResourceNotFoundException();
    }
    return ResponseEntity.ok(service.getAll(orderBy, order));
  }

  @GetMapping("/page/{page}")
  public ResponseEntity<Page<T>> getPage(
      @PathVariable Integer page,
      @RequestParam(required = false) Integer rows,
      @RequestParam(required = false) String orderBy,
      @RequestParam(required = false) String order) {
    return ResponseEntity.ok(service.getAll(page, rows, orderBy, order));
  }

  @GetMapping("/{id}")
  public ResponseEntity<T> get(@PathVariable Long id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping
  public ResponseEntity<T> create(@Valid @RequestBody T newItem, Errors validation) {
    checkForValidationErrors(validation);
    return ResponseEntity.ok(service.create(newItem));
  }

  @PutMapping("/{id}")
  public ResponseEntity<T> update(@PathVariable Long id, @Valid @RequestBody T updatedItem, Errors validation) {
    checkForValidationErrors(validation);
    return ResponseEntity.ok(service.update(id, updatedItem));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok(new MessageResponse("Resource has been deleted."));
  }

  protected void checkForValidationErrors(Errors validation) {
    if (validation.hasErrors())
      throw new FailedValidationException(validation);
  }

}