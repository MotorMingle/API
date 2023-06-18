package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    @NotNull
    private UUID id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    @NotNull
    @Email
    private String email;

    /**
     * Tag is a unique identifier for the user
     */
    @Column(name = "tag", nullable = false, length = 50, unique = true)
    @NotNull
    @Size(min = 1, max = 50)
    private String tag;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    @Past
    private LocalDate birthDate;

    /**
     * Minimum age of the people the user wants to meet
     */
    @Column(name = "minimal_age")
    @Size(min = 18)
    private Integer minimalAge;

    /**
     * Maximum age of the people the user wants to meet
     */
    @Column(name = "maximal_age")
    @Size(max = 150)
    private Integer maximalAge;

}