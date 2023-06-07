package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {

    /**
     * The country id. (ISO 3166-1 alpha-3)
     */
    @Id
    @Column(name = "id", nullable = false, length = 3)
    private String id;

    /**
     * The country name in english.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

}