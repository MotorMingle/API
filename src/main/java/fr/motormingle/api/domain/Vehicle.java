package fr.motormingle.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.motormingle.api.domain.enumeration.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Vehicle entity\n@author brionnevi
 */
@Schema(description = "Vehicle entity\n@author brionnevi")
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "model", length = 50)
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "horse_power")
    private Integer horsePower;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "country", "vehicles" }, allowSetters = true)
    private Manufacturer manufacturer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vehicle id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return this.model;
    }

    public Vehicle model(String model) {
        this.setModel(model);
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return this.year;
    }

    public Vehicle year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHorsePower() {
        return this.horsePower;
    }

    public Vehicle horsePower(Integer horsePower) {
        this.setHorsePower(horsePower);
        return this;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public VehicleType getType() {
        return this.type;
    }

    public Vehicle type(VehicleType type) {
        this.setType(type);
        return this;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Vehicle manufacturer(Manufacturer manufacturer) {
        this.setManufacturer(manufacturer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        return id != null && id.equals(((Vehicle) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", model='" + getModel() + "'" +
            ", year=" + getYear() +
            ", horsePower=" + getHorsePower() +
            ", type='" + getType() + "'" +
            "}";
    }
}
