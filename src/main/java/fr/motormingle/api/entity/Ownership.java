package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Ownership entity describing the possession of a user of a particular
 * vehicle model and linking it to potentials photos.
 */
@Getter
@Setter
@Entity
@Table(name = "ownership")
public class Ownership {

    @EmbeddedId
    private OwnershipId id;

    /**
     * The user owning the vehicle.
     */
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    /**
     * The vehicle owned by the user.
     */
    @MapsId("vehicleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @NotNull
    private Vehicle vehicle;

    @Column(name = "immatriculation")
    private String immatriculation;

}