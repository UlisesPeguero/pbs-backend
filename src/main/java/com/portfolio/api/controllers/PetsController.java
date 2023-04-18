package com.portfolio.api.controllers;


import com.portfolio.api.models.Pet;
import com.portfolio.api.repositories.OwnerRepository;
import com.portfolio.api.repositories.PetRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets")
public class PetsController extends GenericController<Pet> {

    public PetsController(PetRepository petRepository) {
        super(petRepository);
    }

}
