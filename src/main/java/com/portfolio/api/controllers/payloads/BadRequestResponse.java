package com.portfolio.api.controllers.payloads;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestResponse implements Serializable {

  private Integer status;
  private String message;
  private String path;
  private String exceptionMessage;
}
