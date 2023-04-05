package com.portfolio.api.controllers.data;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.portfolio.api.models.AbstractEntity;

import lombok.Data;

@Data
public class ListPayload<T extends AbstractEntity> implements Serializable {

  private int numberRecords;
  private List<T> payload;
}
