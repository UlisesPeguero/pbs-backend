package com.portfolio.api.models;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Version
  protected Integer versionNumber;

  @Column(columnDefinition = "BOOLEAN default true")
  protected boolean active = true;

  

}
