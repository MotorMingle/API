package fr.motormingle.api.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Ownership entity describing the possession of a user of a particular
 * vehicle model and linking it to potentials photos.
 */
@Getter
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
    private User user;

    /**
     * The vehicle owned by the user.
     */
    @MapsId("vehicleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

}