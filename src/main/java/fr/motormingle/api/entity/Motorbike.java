package fr.motormingle.api.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "motorbike")
public class Motorbike extends Vehicle {

    /**
     * Motorbike motorization expressed in cubic centimeters.
     */
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    /**
     * Motorbike type.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "motorbike_type_id", nullable = false)
    private MotorbikeType motorbikeType;

}