package com.portfolio.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api.models.User;
import com.portfolio.api.models.dtos.UserInfoDto;
import com.portfolio.api.repositories.UserRepository;
import com.portfolio.api.security.JwtCookiesManager;
import com.portfolio.api.services.UserDetailsServiceImplementation;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<User> {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  JwtCookiesManager jwtCookiesManager;

  @Autowired
  UserDetailsServiceImplementation userDetailsService;

  public UserController(UserRepository userRepository) {
    super(userRepository, "ADMIN");
  }

  @GetMapping("testToken")
  public ResponseEntity<?> testUserDetails(HttpServletRequest request) {
    String jwt = jwtCookiesManager.getJwtFromCookies(request);
    logger.info("JWT Token to validate: {}", jwt);
    UserDetails userDetails = null;
    if (jwt != null && jwtCookiesManager.validateJwtToken(jwt)) {
      String username = jwtCookiesManager.getUsernameFromJwtToken(jwt);
      userDetails = userDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
          null, userDetails.getAuthorities());

      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    return ResponseEntity.ok(userDetails.getAuthorities());
  }

}
