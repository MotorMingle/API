package fr.motormingle.api.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.motormingle.api.IntegrationTest;
import fr.motormingle.api.domain.Encounter;
import fr.motormingle.api.domain.enumeration.EncounterStatus;
import fr.motormingle.api.repository.EncounterRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EncounterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EncounterResourceIT {

    private static final String DEFAULT_HASH = "AAAAAAAAAA";
    private static final String UPDATED_HASH = "BBBBBBBBBB";

    private static final Integer DEFAULT_COUNT = 1;
    private static final Integer UPDATED_COUNT = 2;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final EncounterStatus DEFAULT_STATUS = EncounterStatus.ACCEPTED;
    private static final EncounterStatus UPDATED_STATUS = EncounterStatus.DECLINED;

    private static final String ENTITY_API_URL = "/api/encounters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EncounterRepository encounterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEncounterMockMvc;

    private Encounter encounter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Encounter createEntity(EntityManager em) {
        Encounter encounter = new Encounter().hash(DEFAULT_HASH).count(DEFAULT_COUNT).date(DEFAULT_DATE).status(DEFAULT_STATUS);
        return encounter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Encounter createUpdatedEntity(EntityManager em) {
        Encounter encounter = new Encounter().hash(UPDATED_HASH).count(UPDATED_COUNT).date(UPDATED_DATE).status(UPDATED_STATUS);
        return encounter;
    }

    @BeforeEach
    public void initTest() {
        encounter = createEntity(em);
    }

    @Test
    @Transactional
    void createEncounter() throws Exception {
        int databaseSizeBeforeCreate = encounterRepository.findAll().size();
        // Create the Encounter
        restEncounterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(encounter)))
            .andExpect(status().isCreated());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeCreate + 1);
        Encounter testEncounter = encounterList.get(encounterList.size() - 1);
        assertThat(testEncounter.getHash()).isEqualTo(DEFAULT_HASH);
        assertThat(testEncounter.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testEncounter.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testEncounter.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createEncounterWithExistingId() throws Exception {
        // Create the Encounter with an existing ID
        encounter.setId(1L);

        int databaseSizeBeforeCreate = encounterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEncounterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(encounter)))
            .andExpect(status().isBadRequest());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEncounters() throws Exception {
        // Initialize the database
        encounterRepository.saveAndFlush(encounter);

        // Get all the encounterList
        restEncounterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(encounter.getId().intValue())))
            .andExpect(jsonPath("$.[*].hash").value(hasItem(DEFAULT_HASH)))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getEncounter() throws Exception {
        // Initialize the database
        encounterRepository.saveAndFlush(encounter);

        // Get the encounter
        restEncounterMockMvc
            .perform(get(ENTITY_API_URL_ID, encounter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(encounter.getId().intValue()))
            .andExpect(jsonPath("$.hash").value(DEFAULT_HASH))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEncounter() throws Exception {
        // Get the encounter
        restEncounterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEncounter() throws Exception {
        // Initialize the database
        encounterRepository.saveAndFlush(encounter);

        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();

        // Update the encounter
        Encounter updatedEncounter = encounterRepository.findById(encounter.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEncounter are not directly saved in db
        em.detach(updatedEncounter);
        updatedEncounter.hash(UPDATED_HASH).count(UPDATED_COUNT).date(UPDATED_DATE).status(UPDATED_STATUS);

        restEncounterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEncounter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEncounter))
            )
            .andExpect(status().isOk());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
        Encounter testEncounter = encounterList.get(encounterList.size() - 1);
        assertThat(testEncounter.getHash()).isEqualTo(UPDATED_HASH);
        assertThat(testEncounter.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testEncounter.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testEncounter.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingEncounter() throws Exception {
        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();
        encounter.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEncounterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, encounter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(encounter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEncounter() throws Exception {
        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();
        encounter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEncounterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(encounter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEncounter() throws Exception {
        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();
        encounter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEncounterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(encounter)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEncounterWithPatch() throws Exception {
        // Initialize the database
        encounterRepository.saveAndFlush(encounter);

        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();

        // Update the encounter using partial update
        Encounter partialUpdatedEncounter = new Encounter();
        partialUpdatedEncounter.setId(encounter.getId());

        partialUpdatedEncounter.date(UPDATED_DATE).status(UPDATED_STATUS);

        restEncounterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEncounter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEncounter))
            )
            .andExpect(status().isOk());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
        Encounter testEncounter = encounterList.get(encounterList.size() - 1);
        assertThat(testEncounter.getHash()).isEqualTo(DEFAULT_HASH);
        assertThat(testEncounter.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testEncounter.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testEncounter.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateEncounterWithPatch() throws Exception {
        // Initialize the database
        encounterRepository.saveAndFlush(encounter);

        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();

        // Update the encounter using partial update
        Encounter partialUpdatedEncounter = new Encounter();
        partialUpdatedEncounter.setId(encounter.getId());

        partialUpdatedEncounter.hash(UPDATED_HASH).count(UPDATED_COUNT).date(UPDATED_DATE).status(UPDATED_STATUS);

        restEncounterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEncounter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEncounter))
            )
            .andExpect(status().isOk());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
        Encounter testEncounter = encounterList.get(encounterList.size() - 1);
        assertThat(testEncounter.getHash()).isEqualTo(UPDATED_HASH);
        assertThat(testEncounter.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testEncounter.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testEncounter.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingEncounter() throws Exception {
        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();
        encounter.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEncounterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, encounter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(encounter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEncounter() throws Exception {
        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();
        encounter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEncounterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(encounter))
            )
            .andExpect(status().isBadRequest());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEncounter() throws Exception {
        int databaseSizeBeforeUpdate = encounterRepository.findAll().size();
        encounter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEncounterMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(encounter))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Encounter in the database
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEncounter() throws Exception {
        // Initialize the database
        encounterRepository.saveAndFlush(encounter);

        int databaseSizeBeforeDelete = encounterRepository.findAll().size();

        // Delete the encounter
        restEncounterMockMvc
            .perform(delete(ENTITY_API_URL_ID, encounter.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Encounter> encounterList = encounterRepository.findAll();
        assertThat(encounterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
