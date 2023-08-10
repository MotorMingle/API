package fr.motormingle.api.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.motormingle.api.IntegrationTest;
import fr.motormingle.api.domain.Interests;
import fr.motormingle.api.repository.InterestsRepository;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link InterestsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InterestsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/interests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InterestsRepository interestsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInterestsMockMvc;

    private Interests interests;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interests createEntity(EntityManager em) {
        Interests interests = new Interests().name(DEFAULT_NAME);
        return interests;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interests createUpdatedEntity(EntityManager em) {
        Interests interests = new Interests().name(UPDATED_NAME);
        return interests;
    }

    @BeforeEach
    public void initTest() {
        interests = createEntity(em);
    }

    @Test
    @Transactional
    void createInterests() throws Exception {
        int databaseSizeBeforeCreate = interestsRepository.findAll().size();
        // Create the Interests
        restInterestsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interests)))
            .andExpect(status().isCreated());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeCreate + 1);
        Interests testInterests = interestsList.get(interestsList.size() - 1);
        assertThat(testInterests.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createInterestsWithExistingId() throws Exception {
        // Create the Interests with an existing ID
        interests.setId(1L);

        int databaseSizeBeforeCreate = interestsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInterestsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interests)))
            .andExpect(status().isBadRequest());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInterests() throws Exception {
        // Initialize the database
        interestsRepository.saveAndFlush(interests);

        // Get all the interestsList
        restInterestsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interests.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getInterests() throws Exception {
        // Initialize the database
        interestsRepository.saveAndFlush(interests);

        // Get the interests
        restInterestsMockMvc
            .perform(get(ENTITY_API_URL_ID, interests.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(interests.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingInterests() throws Exception {
        // Get the interests
        restInterestsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInterests() throws Exception {
        // Initialize the database
        interestsRepository.saveAndFlush(interests);

        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();

        // Update the interests
        Interests updatedInterests = interestsRepository.findById(interests.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedInterests are not directly saved in db
        em.detach(updatedInterests);
        updatedInterests.name(UPDATED_NAME);

        restInterestsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInterests.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInterests))
            )
            .andExpect(status().isOk());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
        Interests testInterests = interestsList.get(interestsList.size() - 1);
        assertThat(testInterests.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingInterests() throws Exception {
        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();
        interests.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, interests.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interests))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInterests() throws Exception {
        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();
        interests.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interests))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInterests() throws Exception {
        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();
        interests.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interests)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInterestsWithPatch() throws Exception {
        // Initialize the database
        interestsRepository.saveAndFlush(interests);

        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();

        // Update the interests using partial update
        Interests partialUpdatedInterests = new Interests();
        partialUpdatedInterests.setId(interests.getId());

        partialUpdatedInterests.name(UPDATED_NAME);

        restInterestsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterests.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterests))
            )
            .andExpect(status().isOk());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
        Interests testInterests = interestsList.get(interestsList.size() - 1);
        assertThat(testInterests.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void fullUpdateInterestsWithPatch() throws Exception {
        // Initialize the database
        interestsRepository.saveAndFlush(interests);

        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();

        // Update the interests using partial update
        Interests partialUpdatedInterests = new Interests();
        partialUpdatedInterests.setId(interests.getId());

        partialUpdatedInterests.name(UPDATED_NAME);

        restInterestsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterests.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterests))
            )
            .andExpect(status().isOk());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
        Interests testInterests = interestsList.get(interestsList.size() - 1);
        assertThat(testInterests.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingInterests() throws Exception {
        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();
        interests.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterestsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, interests.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interests))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInterests() throws Exception {
        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();
        interests.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interests))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInterests() throws Exception {
        int databaseSizeBeforeUpdate = interestsRepository.findAll().size();
        interests.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterestsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(interests))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Interests in the database
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInterests() throws Exception {
        // Initialize the database
        interestsRepository.saveAndFlush(interests);

        int databaseSizeBeforeDelete = interestsRepository.findAll().size();

        // Delete the interests
        restInterestsMockMvc
            .perform(delete(ENTITY_API_URL_ID, interests.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Interests> interestsList = interestsRepository.findAll();
        assertThat(interestsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
