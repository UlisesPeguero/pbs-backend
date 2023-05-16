package com.portfolio.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api.controllers.payloads.AuthenticatedUserResponse;
import com.portfolio.api.controllers.payloads.LoginRequest;
import com.portfolio.api.controllers.payloads.MessageResponse;
import com.portfolio.api.models.User;
import com.portfolio.api.repositories.RoleRepository;
import com.portfolio.api.repositories.UserRepository;
import com.portfolio.api.security.JwtCookiesManager;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private JwtCookiesManager cookiesManager;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, Errors validation) {
    logger.info("Signin attempt: {}", loginRequest.getUsername());

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = (User) authentication.getPrincipal();

    ResponseCookie jwtCookie = cookiesManager.createJwtCookie(user);

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new AuthenticatedUserResponse(user));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = cookiesManager.createEmptyJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("Signed out."));
  }

  @Transactional
  @PostMapping("/testUser")
  public ResponseEntity<?> addTestUser(@Valid @RequestBody User user) {
    logger.info("Attempt to create user: {}", user.getUsername());

    Optional<User> existsUser = userRepository.findByUsername(user.getUsername());
    if (existsUser.isPresent()) {
      return ResponseEntity.ok(new MessageResponse("Exists already."));
    }
    roleRepository.saveAll(user.getRoles());
    user.setPassword(encoder.encode(user.getPassword()));
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User created succesfully!"));

  }
}
