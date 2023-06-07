package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Motorbike type entity.
 */
@Getter
@Setter
@Entity
@Table(name = "motorbike_type")
public class MotorbikeType {

    /**
     * The id of the motorbike type.
     */
    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    private Integer id;

    /**
     * The name of the motorbike type in english.
     */
    @Column(name = "name", nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

}