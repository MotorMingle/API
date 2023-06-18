package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class UserPairStats implements Serializable {
    @Serial
    private static final long serialVersionUID = 5225502204442041057L;

    @Column(name = "date")
    @NotNull
    private LocalDate date;

    @Column(name = "user_1_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Pattern(regexp = "ACCEPTED|DECLINED|PENDING")
    private EncounterStatus user1Status;

    @Column(name = "user_2_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Pattern(regexp = "ACCEPTED|DECLINED|PENDING")
    private EncounterStatus user2Status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserPairStats entity = (UserPairStats) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.user1Status, entity.user1Status) &&
                Objects.equals(this.user2Status, entity.user2Status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, user1Status, user2Status);
    }

}
