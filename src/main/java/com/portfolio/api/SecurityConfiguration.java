package com.portfolio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.portfolio.api.repositories.UserRepository;

@Configuration
public class SecurityConfiguration {

  @Autowired
  private UserRepository userRepository;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return null;
  }

}
