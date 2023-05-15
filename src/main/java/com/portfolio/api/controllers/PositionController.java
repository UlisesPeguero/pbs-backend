package com.portfolio.api.controllers;

import com.portfolio.api.models.Position;
import com.portfolio.api.repositories.PositionRepository;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/positions")
@RolesAllowed("POSITIONS")
public class PositionController extends GenericController<Position> {
    public PositionController(PositionRepository positionRepository) {
        super(positionRepository);
    }

    @GetMapping("/test")
    @RolesAllowed("TEST")
    public String test() {
        return "testing";
    }
}