package com.portfolio.api.controllers;

import com.portfolio.api.models.Position;
import com.portfolio.api.repositories.PositionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/positions")
public class PositionController extends GenericController<Position> {
        protected PositionController(PositionRepository positionRepository) {
            super(positionRepository);
        }
}