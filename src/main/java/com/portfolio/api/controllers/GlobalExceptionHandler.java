package com.portfolio.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portfolio.api.controllers.payloads.BadRequestResponse;
import com.portfolio.api.controllers.payloads.ErrorResponse;
import com.portfolio.api.exceptions.BadRequestException;
import com.portfolio.api.exceptions.FailedValidationException;
import com.portfolio.api.exceptions.ResourceNotFoundException;
import com.portfolio.api.exceptions.UnauthorizedRequestException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException exception) {
    return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = FailedValidationException.class)
  public ResponseEntity<Map<String, Object>> validationException(FailedValidationException exception) {
    Map<String, Object> data = new HashMap<>();
    data.put("error", "Validation errors.");
    data.put("fieldErrors", exception.getErrors().getFieldErrors());
    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {
      BadRequestException.class,
      UnauthorizedRequestException.class
  })
  public ResponseEntity<BadRequestResponse> badRequestException(BadRequestException exception) {
    return new ResponseEntity<>(exception.getResponse(), HttpStatus.valueOf(exception.getResponse().getStatus()));
  }
}
