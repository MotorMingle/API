package fr.motormingle.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ApplicationUser entity\n@author brionnevi
 */
@Schema(description = "ApplicationUser entity\n@author brionnevi")
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "tag", length = 50, unique = true)
    private String tag;

    @Size(max = 50)
    @Column(name = "firstname", length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "description")
    private String description;

    /**
     * Encounter{vehicle} to Vehicle\nEncounter{user} to ApplicationUser
     */
    @Schema(description = "Encounter{vehicle} to Vehicle\nEncounter{user} to ApplicationUser")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicationUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user", "applicationUser" }, allowSetters = true)
    private Set<Encounter> encounters = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_application_user__interests",
        joinColumns = @JoinColumn(name = "application_user_id"),
        inverseJoinColumns = @JoinColumn(name = "interests_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "applicationUsers" }, allowSetters = true)
    private Set<Interests> interests = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "currentVehicle", "vehicle", "user" }, allowSetters = true)
    private Set<Ownership> ownerships = new HashSet<>();

    @JsonIgnoreProperties(value = { "currentVehicle", "vehicle", "user" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "currentVehicle")
    private Ownership ownership;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ApplicationUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public ApplicationUser tag(String tag) {
        this.setTag(tag);
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public ApplicationUser firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public ApplicationUser lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getBirthDate() {
        return this.birthDate;
    }

    public ApplicationUser birthDate(Instant birthDate) {
        this.setBirthDate(birthDate);
        return this;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return this.description;
    }

    public ApplicationUser description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Encounter> getEncounters() {
        return this.encounters;
    }

    public void setEncounters(Set<Encounter> encounters) {
        if (this.encounters != null) {
            this.encounters.forEach(i -> i.setApplicationUser(null));
        }
        if (encounters != null) {
            encounters.forEach(i -> i.setApplicationUser(this));
        }
        this.encounters = encounters;
    }

    public ApplicationUser encounters(Set<Encounter> encounters) {
        this.setEncounters(encounters);
        return this;
    }

    public ApplicationUser addEncounters(Encounter encounter) {
        this.encounters.add(encounter);
        encounter.setApplicationUser(this);
        return this;
    }

    public ApplicationUser removeEncounters(Encounter encounter) {
        this.encounters.remove(encounter);
        encounter.setApplicationUser(null);
        return this;
    }

    public Set<Interests> getInterests() {
        return this.interests;
    }

    public void setInterests(Set<Interests> interests) {
        this.interests = interests;
    }

    public ApplicationUser interests(Set<Interests> interests) {
        this.setInterests(interests);
        return this;
    }

    public ApplicationUser addInterests(Interests interests) {
        this.interests.add(interests);
        interests.getApplicationUsers().add(this);
        return this;
    }

    public ApplicationUser removeInterests(Interests interests) {
        this.interests.remove(interests);
        interests.getApplicationUsers().remove(this);
        return this;
    }

    public Set<Ownership> getOwnerships() {
        return this.ownerships;
    }

    public void setOwnerships(Set<Ownership> ownerships) {
        if (this.ownerships != null) {
            this.ownerships.forEach(i -> i.setUser(null));
        }
        if (ownerships != null) {
            ownerships.forEach(i -> i.setUser(this));
        }
        this.ownerships = ownerships;
    }

    public ApplicationUser ownerships(Set<Ownership> ownerships) {
        this.setOwnerships(ownerships);
        return this;
    }

    public ApplicationUser addOwnerships(Ownership ownership) {
        this.ownerships.add(ownership);
        ownership.setUser(this);
        return this;
    }

    public ApplicationUser removeOwnerships(Ownership ownership) {
        this.ownerships.remove(ownership);
        ownership.setUser(null);
        return this;
    }

    public Ownership getOwnership() {
        return this.ownership;
    }

    public void setOwnership(Ownership ownership) {
        if (this.ownership != null) {
            this.ownership.setCurrentVehicle(null);
        }
        if (ownership != null) {
            ownership.setCurrentVehicle(this);
        }
        this.ownership = ownership;
    }

    public ApplicationUser ownership(Ownership ownership) {
        this.setOwnership(ownership);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            ", tag='" + getTag() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
