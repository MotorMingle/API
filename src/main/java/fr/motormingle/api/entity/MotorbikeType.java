package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Motorbike type entity.
 */
@Getter
@Entity
@Table(name = "motorbike_type")
public class MotorbikeType {

    /**
     * The id of the motorbike type.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * The name of the motorbike type in english.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

}