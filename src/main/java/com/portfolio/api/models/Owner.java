package com.portfolio.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "owners")
public class Owner extends AbstractEntity implements GenericEntity<Owner> {

    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50)
    private String address;
    @Column(length = 50)
    private String address2;
    @Column(length = 12)
    private String phoneNumber;
    @Column(length = 255)
    private String email;


    @Column(nullable = true, length = 64)
    private String photo;

    @Column(length = 200)
    private String notes;

    public void update(Owner updatedOwner) {
        this.firstName = updatedOwner.getFirstName();
        this.lastName = updatedOwner.getLastName();
        this.address = updatedOwner.getAddress();
        this.address2 = updatedOwner.getAddress2();
        this.phoneNumber = updatedOwner.getPhoneNumber();
        this.email = updatedOwner.getEmail();
        this.photo = updatedOwner.getPhoto();
        this.notes = updatedOwner.getNotes();
        this.active = updatedOwner.getActive();
    }
}
