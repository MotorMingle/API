package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
    @NotNull
    private Long id;

    private OwnershipId ownershipId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhotoId entity = (PhotoId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.ownershipId, entity.ownershipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownershipId);
    }

}
