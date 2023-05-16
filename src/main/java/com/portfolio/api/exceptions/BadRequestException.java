package com.portfolio.api.exceptions;

import com.portfolio.api.controllers.payloads.BadRequestResponse;

import jakarta.servlet.http.HttpServletResponse;

public class BadRequestException extends RuntimeException {

  protected final BadRequestResponse response;

  public BadRequestException(String message, String path) {
    this(HttpServletResponse.SC_BAD_REQUEST, message, path, "");
  }

  public BadRequestException(Integer status, String message, String path, String exceptioMessage) {
    this.response = new BadRequestResponse(status, message, path, exceptioMessage);
  }

  public BadRequestResponse getResponse() {
    return response;
  }
}
