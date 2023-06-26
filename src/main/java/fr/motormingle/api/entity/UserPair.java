package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class UserPair {

    @EmbeddedId
    private UserPairId id;

    @MapsId("userId1")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id_1", nullable = false)
    @NotNull
    private Mingler mingler1;

    @MapsId("userId2")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id_2", nullable = false)
    @NotNull
    private Mingler mingler2;

}
