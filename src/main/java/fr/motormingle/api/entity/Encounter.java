package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "encounter")
public class Encounter extends UserPair {

    /**
     * Hash of the H3 hexagonal cell identifier of the encounter.
     */
    @Column(name = "hash", nullable = false)
    @NotNull
    private String hash;

    /**
     * Number of encounters between the two users.
     */
    @Column(name = "count", nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private int count;

    /**
     * Contains :
     * <ul>
     *  <li>the date of the last encounter between the two users.</li>
     *  <li>the user1's status</li>
     *  <li>the user2's status</li>
     * </ul>
     */
    @Embedded
    private UserPairStats userPairStats;
}