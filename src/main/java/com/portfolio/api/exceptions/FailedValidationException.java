package com.portfolio.api.exceptions;

import org.springframework.validation.Errors;

public class FailedValidationException extends RuntimeException {
  private final Errors errors;

  public FailedValidationException(Errors errors) {
    this.errors = errors;
  }

  public Errors getErrors() {
    return errors;
  }
}
