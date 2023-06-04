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
public class EncounterId implements Serializable {
    @Serial
    private static final long serialVersionUID = -1888100727307985572L;
    @Column(name = "user_id_1", nullable = false, length = 50)
    private String userId1;

    @Column(name = "user_id_2", nullable = false, length = 50)
    private String userId2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EncounterId entity = (EncounterId) o;
        return Objects.equals(this.userId1, entity.userId1) &&
                Objects.equals(this.userId2, entity.userId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2);
    }

}