package com.portfolio.api.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.portfolio.api.models.AbstractEntity;
import com.portfolio.api.repositories.GenericRepository;

public abstract class GenericService<T extends AbstractEntity> {

  protected final GenericRepository<T> repository;

  protected GenericService(GenericRepository<T> repository) {
    this.repository = repository;
  }

  public Page<T> findAll(Integer pageNumber, Integer rowsPerPage, String orderBy, String order) {
    Pageable page;
    Sort sort = getSorter(orderBy, order);
    page = PageRequest.of(pageNumber, rowsPerPage == null ? 15 : rowsPerPage, sort);
    return repository.findAll(page);
  }

  public List<T> findAll(String orderBy, String order) {
    Sort sort = getSorter(orderBy, order);
    return repository.findAll(sort);
  }

  public List<T> findAll() {
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

}
