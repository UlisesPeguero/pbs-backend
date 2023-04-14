package com.portfolio.api.models;

public interface GenericEntity<T> {
  public void update(T updatedItem);
}
