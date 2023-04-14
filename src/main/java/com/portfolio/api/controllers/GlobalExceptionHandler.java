package com.portfolio.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portfolio.api.controllers.data.ErrorPayload;
import com.portfolio.api.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorPayload> resourceNotFoundException(ResourceNotFoundException exception) {
    return new ResponseEntity<>(new ErrorPayload(exception.getMessage()), HttpStatus.NOT_FOUND);
  }

}
