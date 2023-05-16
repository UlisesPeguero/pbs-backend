package com.portfolio.api.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class UnauthorizedRequestException extends BadRequestException {

  public UnauthorizedRequestException(String path) {
    this("Unauthorized request.", path);
  }

  public UnauthorizedRequestException(String message, String path) {
    this(message, path, "");
  }

  public UnauthorizedRequestException(String message, String path, String exceptionMessage) {
    super(HttpServletResponse.SC_UNAUTHORIZED, message, path, exceptionMessage);
  }

}
