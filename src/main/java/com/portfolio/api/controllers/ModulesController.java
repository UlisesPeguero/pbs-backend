package com.portfolio.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api.models.Module;
import com.portfolio.api.repositories.ModuleRepository;

@RestController
@RequestMapping("/api/modules")
public class ModulesController extends GenericController<Module> {

  public ModulesController(ModuleRepository moduleRepository) {
    super(moduleRepository);
  }
}
