package fr.motormingle.api.entity;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mingler")
public class Mingler implements Serializable {

    /**
     * Unique identifier for the user provided by firebase auth
     */
    @Id
    @NotNull
    @NonNull
    @Column(name = "id", length = 20, nullable = false, unique = true)
    private String id;

    @Column(name = "email", length = 50, unique = true)
    @NotNull
    @NonNull
    @Email
    private String email;

    /**
     * Tag is a unique identifier for the user
     */
    @Column(name = "tag", length = 50, unique = true)
    @NotNull
    @NonNull
    @Size(min = 1, max = 50)
    private String tag;

    @Column(name = "first_name", length = 50)
    @NotNull
    @NonNull
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    @NotNull
    @NonNull
    @Size(min = 1, max = 50)
    private String lastName;

    @Column(name = "birth_date")
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

    public MinglerTagGet toMinglerTagGet() {
        return new MinglerTagGet(tag);
    }
}