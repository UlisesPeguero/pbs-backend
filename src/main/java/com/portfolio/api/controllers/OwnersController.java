package com.portfolio.api.controllers;

import com.portfolio.api.models.Owner;
import com.portfolio.api.repositories.OwnerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owners")
public class OwnersController extends GenericController<Owner> {

    public OwnersController(OwnerRepository ownerRepository) {
        super(ownerRepository, "OWNERS");
    }
}
