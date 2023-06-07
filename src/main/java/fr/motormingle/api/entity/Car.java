package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends Vehicle {

    /**
     * Car motorization expressed in liters.
     */
    @Column(name = "capacity", nullable = false)
    @Digits(integer = 2, fraction = 1)
    private Double capacity;

}