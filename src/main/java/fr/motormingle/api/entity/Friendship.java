package fr.motormingle.api.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "friendship")
@Entity
public class Friendship extends UserPair {

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
