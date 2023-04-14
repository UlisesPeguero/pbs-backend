package com.portfolio.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "modules")
public class Module extends AbstractEntity implements GenericEntity<Module> {

  @Column(length = 20)
  private String path;

  @Column(length = 20)
  private String name;

  @Column(length = 25)
  private String icon;

  @Column(length = 25)
  private String role;

  public void update(Module updatedModule) {
    this.path = updatedModule.getPath();
    this.name = updatedModule.getName();
    this.icon = updatedModule.getIcon();
    this.role = updatedModule.getRole();
    this.active = updatedModule.getActive();
  }
}
