package com.portfolio.api.controllers.payloads;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorResponse implements Serializable {
  private String error;

  public ErrorResponse(String error) {
    this.error = error;
  }
}
