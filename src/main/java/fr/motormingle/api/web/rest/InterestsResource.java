package fr.motormingle.api.web.rest;

import fr.motormingle.api.domain.Interests;
import fr.motormingle.api.repository.InterestsRepository;
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
 * REST controller for managing {@link fr.motormingle.api.domain.Interests}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InterestsResource {

    private final Logger log = LoggerFactory.getLogger(InterestsResource.class);

    private static final String ENTITY_NAME = "interests";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InterestsRepository interestsRepository;

    public InterestsResource(InterestsRepository interestsRepository) {
        this.interestsRepository = interestsRepository;
    }

    /**
     * {@code POST  /interests} : Create a new interests.
     *
     * @param interests the interests to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interests, or with status {@code 400 (Bad Request)} if the interests has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interests")
    public ResponseEntity<Interests> createInterests(@Valid @RequestBody Interests interests) throws URISyntaxException {
        log.debug("REST request to save Interests : {}", interests);
        if (interests.getId() != null) {
            throw new BadRequestAlertException("A new interests cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Interests result = interestsRepository.save(interests);
        return ResponseEntity
            .created(new URI("/api/interests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interests/:id} : Updates an existing interests.
     *
     * @param id the id of the interests to save.
     * @param interests the interests to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interests,
     * or with status {@code 400 (Bad Request)} if the interests is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interests couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interests/{id}")
    public ResponseEntity<Interests> updateInterests(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Interests interests
    ) throws URISyntaxException {
        log.debug("REST request to update Interests : {}, {}", id, interests);
        if (interests.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interests.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Interests result = interestsRepository.save(interests);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interests.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /interests/:id} : Partial updates given fields of an existing interests, field will ignore if it is null
     *
     * @param id the id of the interests to save.
     * @param interests the interests to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interests,
     * or with status {@code 400 (Bad Request)} if the interests is not valid,
     * or with status {@code 404 (Not Found)} if the interests is not found,
     * or with status {@code 500 (Internal Server Error)} if the interests couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/interests/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Interests> partialUpdateInterests(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Interests interests
    ) throws URISyntaxException {
        log.debug("REST request to partial update Interests partially : {}, {}", id, interests);
        if (interests.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interests.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interestsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Interests> result = interestsRepository
            .findById(interests.getId())
            .map(existingInterests -> {
                if (interests.getName() != null) {
                    existingInterests.setName(interests.getName());
                }

                return existingInterests;
            })
            .map(interestsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interests.getId().toString())
        );
    }

    /**
     * {@code GET  /interests} : get all the interests.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interests in body.
     */
    @GetMapping("/interests")
    public List<Interests> getAllInterests() {
        log.debug("REST request to get all Interests");
        return interestsRepository.findAll();
    }

    /**
     * {@code GET  /interests/:id} : get the "id" interests.
     *
     * @param id the id of the interests to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interests, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interests/{id}")
    public ResponseEntity<Interests> getInterests(@PathVariable Long id) {
        log.debug("REST request to get Interests : {}", id);
        Optional<Interests> interests = interestsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(interests);
    }

    /**
     * {@code DELETE  /interests/:id} : delete the "id" interests.
     *
     * @param id the id of the interests to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interests/{id}")
    public ResponseEntity<Void> deleteInterests(@PathVariable Long id) {
        log.debug("REST request to delete Interests : {}", id);
        interestsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
