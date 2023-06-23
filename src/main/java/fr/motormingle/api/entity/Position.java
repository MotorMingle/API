package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "position")
@Entity
public class Position {

    @EmbeddedId
    private PositionId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "latitude", nullable = false)
    @NotNull
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @NotNull
    private Double longitude;

    @Column(name = "treatment_status", nullable = false)
    @NotNull
    private String treatmentStatus;

}
