package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "motorbike")
public class Motorbike extends Vehicle {

    /**
     * Motorbike motorization expressed in cubic centimeters.
     */
    @Column(name = "capacity")
    @Digits(integer = 4, fraction = 0)
    private Integer capacity;

    /**
     * Motorbike type.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "motorbike_type_id", nullable = false)
    @NotNull
    private MotorbikeType motorbikeType;

}