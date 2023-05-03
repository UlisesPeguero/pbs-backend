package com.portfolio.api.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class User extends AbstractEntity implements GenericEntity<User>, UserDetails {

  @NotNull(message = "The username cannot be null.")
  @Size(min = 5, max = 30, message = "The username must be between 5 and 30 characters long.")
  @Column(length = 30)
  private String username;

  @NotBlank
  @Column(length = 128)
  @JsonIgnore
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  // TODO: Add Employee model to the user
  // @OneToOne
  // private Employee employee;

  @Transient
  private List<? extends GrantedAuthority> authorities = null;

  public void update(User updatedUser) {
    this.roles = updatedUser.getRoles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (authorities == null) {
      authorities = this.getRoles()
          .stream()
          .map(role -> new SimpleGrantedAuthority(role.getName()))
          .collect(Collectors.toList());
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
