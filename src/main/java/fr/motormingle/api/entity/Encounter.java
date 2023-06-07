package fr.motormingle.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "encounter")
public class Encounter {
    @EmbeddedId
    private EncounterId id;

    @MapsId("userId1")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_1", nullable = false)
    private User userId1;

    @MapsId("userId2")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_2", nullable = false)
    private User userId2;

    /**
     * Hash of the H3 hexagonal cell identifier of the encounter.
     */
    @Column(name = "hash", nullable = false)
    private String hash;

    /**
     * Number of encounters between the two users.
     */
    @Column(name = "count", nullable = false)
    private int count;

    /**
     * Last date of the encounter.
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /**
     * The status of the encounter enumerated by the {@code EncounterStatus} enum.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EncounterStatus status;

}