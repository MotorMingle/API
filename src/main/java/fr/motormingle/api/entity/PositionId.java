package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PositionId implements Serializable {
    @Serial
    private static final long serialVersionUID = 4877203349573100342L;

    @Column(name = "user_id", nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String userId;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDateTime date;

    /**
     * @param o Object to compare
     * @return true if both of user IDs are the same either way
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PositionId entity = (PositionId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.date, entity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, date);
    }
}
