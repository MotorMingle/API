package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Photo composite identifier class
 */

@Getter
@Setter
@Embeddable
public class PhotoId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6913888454633548211L;

    @Column(name = "id", nullable = false)
    private Long id;

    private OwnershipId ownershipId;
}
