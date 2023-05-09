package com.portfolio.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "positions")
public class Position extends AbstractEntity implements GenericEntity<Position>{

    @NotBlank
    @Size(max = 50, message = "Name cannot be longer than 100 characters.")
    private String name;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "position_id")
    private List<Employee> employees = new ArrayList<>();

    public void update(Position updatedPosition) {
        this.name = updatedPosition.getName();
    }

}
