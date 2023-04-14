package com.portfolio.api.controllers.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorPayload implements Serializable {
  private String error;

  public ErrorPayload(String error) {
    this.error = error;
  }
}
