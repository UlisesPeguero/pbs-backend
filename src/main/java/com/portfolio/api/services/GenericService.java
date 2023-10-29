package com.portfolio.api.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.portfolio.api.exceptions.ResourceNotFoundException;
import com.portfolio.api.models.GenericEntity;
import com.portfolio.api.repositories.GenericRepository;

import jakarta.transaction.Transactional;

public abstract class GenericService<T extends GenericEntity<T>> {

  protected final GenericRepository<T> repository;
  protected final String resourceName;

  protected GenericService(GenericRepository<T> repository) {
    this(repository, "resource");
  }

  protected GenericService(GenericRepository<T> repository, String resourceName) {
    this.repository = repository;
    this.resourceName = resourceName;
  }

  public Page<T> getAll(Integer pageNumber, Integer rowsPerPage, String orderBy, String order) {
    Pageable page;
    Sort sort = getSorter(orderBy, order);
    page = PageRequest.of(pageNumber, rowsPerPage == null ? 15 : rowsPerPage, sort);
    return repository.findAll(page);
  }

  public List<T> getAll(String orderBy, String order) {
    Sort sort = getSorter(orderBy, order);
    return repository.findAll(sort);
  }

  // public <S> List<S> getAll(String orderBy, String order, Class<S> type) {
  // Sort sort = getSorter(orderBy, order);
  // return repository.getGridView(sort, type);
  // }

  public List<?> getGridAll(String orderBy, String order) {
    Sort sort = getSorter(orderBy, order);
    return repository.getGridView(sort);
  }

  public List<T> getAll() {
    return repository.findAll();
  }

  protected Sort getSorter(String orderBy, String order) {
    Sort sort = Sort.unsorted();
    if (orderBy != null || order != null) {
      sort = Sort.by(orderBy);
      sort = "DESC".equalsIgnoreCase(order) ? sort.descending() : sort.ascending();
    }
    return sort;
  }

  public T get(Long id) throws ResourceNotFoundException {
    return this.repository.findById(id).orElseThrow(
        () -> this.getResourceNotFoundException(id));
  }

  @Transactional
  public T create(T newItem) {
    return repository.saveAndFlush(newItem);
  }

  @Transactional
  public T update(Long id, T updatedItem) throws ResourceNotFoundException {
    T original = get(id);
    original.update(updatedItem);
    return repository.saveAndFlush(original);
  }

  protected boolean canBeDeleted(T item) {
    return true;
  }

  @Transactional
  public void delete(Long id) throws ResourceNotFoundException {
    if (!canBeDeleted(get(id)))
      return;
    repository.deleteById(id);
  }

  private ResourceNotFoundException getResourceNotFoundException(long id) {
    return new ResourceNotFoundException("The " + resourceName + " with ID:" + id + " was not found.");
  }
}
