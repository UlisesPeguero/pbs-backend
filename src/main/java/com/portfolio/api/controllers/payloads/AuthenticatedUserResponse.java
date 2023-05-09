package com.portfolio.api.controllers.payloads;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.portfolio.api.models.User;

import lombok.Data;

@Data
public class AuthenticatedUserResponse implements Serializable {

  private Long id;
  private String username;
  private List<String> roles;

  public AuthenticatedUserResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
  }

}
