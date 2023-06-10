package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "friendship")
@Entity
public class Friendship {
    @EmbeddedId
    private FriendshipId id;

    @MapsId("userId1")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_1", nullable = false)
    @NotNull
    private User userId1;

    @MapsId("userId2")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_2", nullable = false)
    @NotNull
    private User userId2;

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
}
