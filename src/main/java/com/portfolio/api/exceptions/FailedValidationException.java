package com.portfolio.api.exceptions;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class FailedValidationException extends RuntimeException {
  final private Errors errors;

  public FailedValidationException(Errors errors) {
    this.errors = errors;
  }

  public Errors getErrors() {
    return errors;
  }
}
