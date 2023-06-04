package fr.motormingle.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "motorbike")
public class Motorbike extends Vehicle {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "motorbike_type_id", nullable = false)
    private MotorbikeType motorbikeType;

}