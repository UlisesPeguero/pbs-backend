package com.portfolio.api.exceptions;

public class UnsafeResourceDeletionException extends RuntimeException {
  private String message;

  public UnsafeResourceDeletionException(String message) {
    this.message = message;
  }

  public UnsafeResourceDeletionException() {
  }
}
