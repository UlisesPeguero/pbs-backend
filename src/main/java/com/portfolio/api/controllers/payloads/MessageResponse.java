package com.portfolio.api.controllers.payloads;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageResponse implements Serializable {
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }
}
