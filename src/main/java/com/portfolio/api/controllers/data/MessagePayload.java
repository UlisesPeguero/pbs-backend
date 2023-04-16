package com.portfolio.api.controllers.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessagePayload implements Serializable {
  private String message;

  public MessagePayload(String message) {
    this.message = message;
  }
}
