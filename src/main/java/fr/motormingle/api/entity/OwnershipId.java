package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OwnershipId implements Serializable {
    @Serial
    private static final long serialVersionUID = 2249956555448919268L;
    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "vehicle_id", nullable = false)
    private Integer vehicleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OwnershipId entity = (OwnershipId) o;
        return Objects.equals(this.vehicleId, entity.vehicleId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, userId);
    }

}