package fr.motormingle.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends Vehicle {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

}