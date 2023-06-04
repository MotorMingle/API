package fr.motormingle.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "horse_power", nullable = false)
    private Integer horsePower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

}