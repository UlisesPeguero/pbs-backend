package com.portfolio.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity implements GenericEntity<Role> {

  @NotNull
  @Column(length = 20)
  private String name;

  public Role() {
  }

  public Role(String name) {
    super();
    this.name = name;
  }

  public void update(Role updatedRole) {
    this.name = updatedRole.getName();
  }
}
