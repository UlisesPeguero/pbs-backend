package com.portfolio.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employees")
public class Employee extends AbstractEntity implements GenericEntity<Employee> {
    @NotBlank(message = "First name cannot be empty.")
    @Size(max = 50, message = "First name cannot be longer than 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty.")
    @Size(max = 50, message = "Last name cannot be longer than 50 characters.")
    private String lastName;

    @Valid
    @NotNull(message = "Select a valid job position.")
    @ManyToOne
    private Position position;

    @NotBlank(message = "Address cannot be empty.")
    @Size(max = 100, message = "Address cannot be longer than 100 characters.")
    private String address;

    @Size(max = 100, message = "Address 2 cannot be longer than 100 characters.")
    private String address2;

    @Size(min = 10, message = "Phone number cannot be shorter than 10 characters.")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be empty.")
    @Email
    private String email;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    @Override
    public void update(Employee updatedItem) {

    }
}
