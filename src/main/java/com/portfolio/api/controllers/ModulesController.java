package com.portfolio.api.controllers;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api.models.Module;
import com.portfolio.api.repositories.ModuleRepository;

import jakarta.annotation.security.RolesAllowed;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modules")
@RolesAllowed("MODULES")
public class ModulesController extends GenericController<Module> {

  public ModulesController(ModuleRepository moduleRepository) {
    super(moduleRepository);
  }
}
