package fr.motormingle.api.web.rest;

import fr.motormingle.api.domain.Ownership;
import fr.motormingle.api.repository.OwnershipRepository;
import fr.motormingle.api.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link fr.motormingle.api.domain.Ownership}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OwnershipResource {

    private final Logger log = LoggerFactory.getLogger(OwnershipResource.class);

    private static final String ENTITY_NAME = "ownership";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OwnershipRepository ownershipRepository;

    public OwnershipResource(OwnershipRepository ownershipRepository) {
        this.ownershipRepository = ownershipRepository;
    }

    /**
     * {@code POST  /ownerships} : Create a new ownership.
     *
     * @param ownership the ownership to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ownership, or with status {@code 400 (Bad Request)} if the ownership has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ownerships")
    public ResponseEntity<Ownership> createOwnership(@RequestBody Ownership ownership) throws URISyntaxException {
        log.debug("REST request to save Ownership : {}", ownership);
        if (ownership.getId() != null) {
            throw new BadRequestAlertException("A new ownership cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ownership result = ownershipRepository.save(ownership);
        return ResponseEntity
            .created(new URI("/api/ownerships/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ownerships/:id} : Updates an existing ownership.
     *
     * @param id the id of the ownership to save.
     * @param ownership the ownership to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ownership,
     * or with status {@code 400 (Bad Request)} if the ownership is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ownership couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ownerships/{id}")
    public ResponseEntity<Ownership> updateOwnership(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Ownership ownership
    ) throws URISyntaxException {
        log.debug("REST request to update Ownership : {}, {}", id, ownership);
        if (ownership.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ownership.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ownershipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Ownership result = ownershipRepository.save(ownership);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ownership.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ownerships/:id} : Partial updates given fields of an existing ownership, field will ignore if it is null
     *
     * @param id the id of the ownership to save.
     * @param ownership the ownership to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ownership,
     * or with status {@code 400 (Bad Request)} if the ownership is not valid,
     * or with status {@code 404 (Not Found)} if the ownership is not found,
     * or with status {@code 500 (Internal Server Error)} if the ownership couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ownerships/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Ownership> partialUpdateOwnership(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Ownership ownership
    ) throws URISyntaxException {
        log.debug("REST request to partial update Ownership partially : {}, {}", id, ownership);
        if (ownership.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ownership.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ownershipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Ownership> result = ownershipRepository
            .findById(ownership.getId())
            .map(existingOwnership -> {
                if (ownership.getLicensePlate() != null) {
                    existingOwnership.setLicensePlate(ownership.getLicensePlate());
                }

                return existingOwnership;
            })
            .map(ownershipRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ownership.getId().toString())
        );
    }

    /**
     * {@code GET  /ownerships} : get all the ownerships.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ownerships in body.
     */
    @GetMapping("/ownerships")
    public List<Ownership> getAllOwnerships() {
        log.debug("REST request to get all Ownerships");
        return ownershipRepository.findAll();
    }

    /**
     * {@code GET  /ownerships/:id} : get the "id" ownership.
     *
     * @param id the id of the ownership to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ownership, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ownerships/{id}")
    public ResponseEntity<Ownership> getOwnership(@PathVariable Long id) {
        log.debug("REST request to get Ownership : {}", id);
        Optional<Ownership> ownership = ownershipRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ownership);
    }

    /**
     * {@code DELETE  /ownerships/:id} : delete the "id" ownership.
     *
     * @param id the id of the ownership to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ownerships/{id}")
    public ResponseEntity<Void> deleteOwnership(@PathVariable Long id) {
        log.debug("REST request to delete Ownership : {}", id);
        ownershipRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
