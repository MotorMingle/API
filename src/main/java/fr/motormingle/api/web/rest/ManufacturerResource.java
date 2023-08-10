package fr.motormingle.api.web.rest;

import fr.motormingle.api.domain.Manufacturer;
import fr.motormingle.api.repository.ManufacturerRepository;
import fr.motormingle.api.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link fr.motormingle.api.domain.Manufacturer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ManufacturerResource.class);

    private static final String ENTITY_NAME = "manufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerResource(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * {@code POST  /manufacturers} : Create a new manufacturer.
     *
     * @param manufacturer the manufacturer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufacturer, or with status {@code 400 (Bad Request)} if the manufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> createManufacturer(@Valid @RequestBody Manufacturer manufacturer) throws URISyntaxException {
        log.debug("REST request to save Manufacturer : {}", manufacturer);
        if (manufacturer.getId() != null) {
            throw new BadRequestAlertException("A new manufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Manufacturer result = manufacturerRepository.save(manufacturer);
        return ResponseEntity
            .created(new URI("/api/manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manufacturers/:id} : Updates an existing manufacturer.
     *
     * @param id the id of the manufacturer to save.
     * @param manufacturer the manufacturer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturer,
     * or with status {@code 400 (Bad Request)} if the manufacturer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufacturer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Manufacturer manufacturer
    ) throws URISyntaxException {
        log.debug("REST request to update Manufacturer : {}, {}", id, manufacturer);
        if (manufacturer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, manufacturer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!manufacturerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Manufacturer result = manufacturerRepository.save(manufacturer);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manufacturer.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /manufacturers/:id} : Partial updates given fields of an existing manufacturer, field will ignore if it is null
     *
     * @param id the id of the manufacturer to save.
     * @param manufacturer the manufacturer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufacturer,
     * or with status {@code 400 (Bad Request)} if the manufacturer is not valid,
     * or with status {@code 404 (Not Found)} if the manufacturer is not found,
     * or with status {@code 500 (Internal Server Error)} if the manufacturer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/manufacturers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Manufacturer> partialUpdateManufacturer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Manufacturer manufacturer
    ) throws URISyntaxException {
        log.debug("REST request to partial update Manufacturer partially : {}, {}", id, manufacturer);
        if (manufacturer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, manufacturer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!manufacturerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Manufacturer> result = manufacturerRepository
            .findById(manufacturer.getId())
            .map(existingManufacturer -> {
                if (manufacturer.getName() != null) {
                    existingManufacturer.setName(manufacturer.getName());
                }

                return existingManufacturer;
            })
            .map(manufacturerRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manufacturer.getId().toString())
        );
    }

    /**
     * {@code GET  /manufacturers} : get all the manufacturers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufacturers in body.
     */
    @GetMapping("/manufacturers")
    public List<Manufacturer> getAllManufacturers() {
        log.debug("REST request to get all Manufacturers");
        return manufacturerRepository.findAll();
    }

    /**
     * {@code GET  /manufacturers/:id} : get the "id" manufacturer.
     *
     * @param id the id of the manufacturer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufacturer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable Long id) {
        log.debug("REST request to get Manufacturer : {}", id);
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(manufacturer);
    }

    /**
     * {@code DELETE  /manufacturers/:id} : delete the "id" manufacturer.
     *
     * @param id the id of the manufacturer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manufacturers/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete Manufacturer : {}", id);
        manufacturerRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
