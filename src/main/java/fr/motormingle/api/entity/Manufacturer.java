package fr.motormingle.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    /**
     * The id of the manufacturer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manufacturer_id_seq")
    @SequenceGenerator(name = "manufacturer_id_seq", sequenceName = "manufacturer_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    /**
     * The name of the manufacturer.
     */
    @Column(name = "name", nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    /**
     * Origin country of the manufacturer.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    @NotNull
    private Country country;

}