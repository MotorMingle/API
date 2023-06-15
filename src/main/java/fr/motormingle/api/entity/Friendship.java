package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "friendship")
@Entity
public class Friendship {
    @EmbeddedId
    private UserPair id;

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

    /**
     * Contains :
     * <ul>
     *  <li>the date of the friendship between the two users.</li>
     *  <li>the user1's status</li>
     *  <li>the user2's status</li>
     * </ul>
     */
    @Embedded
    private UserPairStats userPairStats;
}
