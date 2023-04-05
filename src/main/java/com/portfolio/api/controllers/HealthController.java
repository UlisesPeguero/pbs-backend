package com.portfolio.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/health")
public class HealthController {

  @GetMapping
  public ResponseEntity<HealthData> getHealth() {
    return ResponseEntity.ok(new HealthData(1.0f, "Online"));
  }
  
}

class HealthData {
  public final float version;
  public final String status;

  public HealthData(float version, String status) {
    this.version = version;
    this.status = status;
  }
}