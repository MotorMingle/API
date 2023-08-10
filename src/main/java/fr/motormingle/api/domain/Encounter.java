package fr.motormingle.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.motormingle.api.domain.enumeration.EncounterStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Encounter entity\n@author brionnevi
 */
@Schema(description = "Encounter entity\n@author brionnevi")
@Entity
@Table(name = "encounter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Encounter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "count")
    private Integer count;

    @Column(name = "date")
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EncounterStatus status;

    /**
     * Encounter{vehicle} to Vehicle\nEncounter{user} to ApplicationUser
     */
    @Schema(description = "Encounter{vehicle} to Vehicle\nEncounter{user} to ApplicationUser")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "encounters", "interests", "ownerships", "ownership" }, allowSetters = true)
    private ApplicationUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "encounters", "interests", "ownerships", "ownership" }, allowSetters = true)
    private ApplicationUser applicationUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Encounter id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return this.hash;
    }

    public Encounter hash(String hash) {
        this.setHash(hash);
        return this;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getCount() {
        return this.count;
    }

    public Encounter count(Integer count) {
        this.setCount(count);
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Instant getDate() {
        return this.date;
    }

    public Encounter date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public EncounterStatus getStatus() {
        return this.status;
    }

    public Encounter status(EncounterStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(EncounterStatus status) {
        this.status = status;
    }

    public ApplicationUser getUser() {
        return this.user;
    }

    public void setUser(ApplicationUser applicationUser) {
        this.user = applicationUser;
    }

    public Encounter user(ApplicationUser applicationUser) {
        this.setUser(applicationUser);
        return this;
    }

    public ApplicationUser getApplicationUser() {
        return this.applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Encounter applicationUser(ApplicationUser applicationUser) {
        this.setApplicationUser(applicationUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Encounter)) {
            return false;
        }
        return id != null && id.equals(((Encounter) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Encounter{" +
            "id=" + getId() +
            ", hash='" + getHash() + "'" +
            ", count=" + getCount() +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
