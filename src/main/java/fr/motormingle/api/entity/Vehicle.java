package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Vehicle abstract class
 */
@Getter
@Setter
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id_seq")
    @SequenceGenerator(name = "vehicle_id_seq", sequenceName = "vehicle_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    /**
     * Model name of the vehicle
     */
    @Column(name = "model", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    private String model;

    /**
     * Year of the vehicle
     */
    @Column(name = "year", nullable = false)
    @NotNull
    @Size(min = 4, max = 4)
    @Digits(integer = 4, fraction = 0)
    private Integer year;

    /**
     * Horsepower of the vehicle
     */
    @Column(name = "horse_power")
    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer horsePower;

    /**
     * Manufacturer of the vehicle
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

}