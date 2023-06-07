package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo {

    @EmbeddedId
    @NotNull
    private PhotoId id;

    @Lob
    @Column(name = "content", nullable = false)
    private Blob content;

    @MapsId("ownershipId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ownership_user_id", referencedColumnName = "user_id")
    @JoinColumn(name = "ownership_vehicle_id", referencedColumnName = "vehicle_id")
    private Ownership ownership;

}