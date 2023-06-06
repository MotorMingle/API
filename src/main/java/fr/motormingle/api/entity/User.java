package fr.motormingle.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private UUID id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "tag", nullable = false, length = 50, unique = true)
    private String tag;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "minimal_age")
    private Integer minimalAge;

    @Column(name = "maximal_age")
    private Integer maximalAge;

}