package com.portfolio.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pets")
public class Pet extends AbstractEntity implements GenericEntity<Pet> {

    @Column(length = 25)
    private String petName;

    @Column(length = 25)
    private String breed;
    @Column(length = 200)
    private String notes;
    @Column(nullable = true, length = 64)
    private String photo;

    public void update(Pet updatedPet) {
        this.petName = updatedPet.getPetName();
        this.breed = updatedPet.getBreed();
        this.notes = updatedPet.getNotes();
        this.photo = updatedPet.getPhoto();
        this.photo = updatedPet.getPhoto();
        this.notes = updatedPet.getNotes();
        this.active = updatedPet.getActive();
    }
}
