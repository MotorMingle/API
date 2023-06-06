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

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EncounterStatus status;

}