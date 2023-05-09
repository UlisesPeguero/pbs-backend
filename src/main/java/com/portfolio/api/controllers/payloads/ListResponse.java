package com.portfolio.api.controllers.payloads;

import java.io.Serializable;
import java.util.List;

import com.portfolio.api.models.AbstractEntity;

import lombok.Data;

@Data
public class ListResponse<T extends AbstractEntity> implements Serializable {

  private int numberRecords;
  private List<T> payload;

  public ListResponse() {
  }

  public ListResponse(List<T> payload) {
    setPayload(payload);
  }

  public void setPayload(List<T> payload) {
    this.payload = payload;
    this.numberRecords = payload.size();
  }
}
