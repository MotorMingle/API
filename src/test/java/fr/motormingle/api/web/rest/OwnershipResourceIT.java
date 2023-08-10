package fr.motormingle.api.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.motormingle.api.IntegrationTest;
import fr.motormingle.api.domain.Ownership;
import fr.motormingle.api.repository.OwnershipRepository;
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
 * Integration tests for the {@link OwnershipResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OwnershipResourceIT {

    private static final String DEFAULT_LICENSE_PLATE = "AAAAAAAAAA";
    private static final String UPDATED_LICENSE_PLATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ownerships";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OwnershipRepository ownershipRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOwnershipMockMvc;

    private Ownership ownership;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ownership createEntity(EntityManager em) {
        Ownership ownership = new Ownership().licensePlate(DEFAULT_LICENSE_PLATE);
        return ownership;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ownership createUpdatedEntity(EntityManager em) {
        Ownership ownership = new Ownership().licensePlate(UPDATED_LICENSE_PLATE);
        return ownership;
    }

    @BeforeEach
    public void initTest() {
        ownership = createEntity(em);
    }

    @Test
    @Transactional
    void createOwnership() throws Exception {
        int databaseSizeBeforeCreate = ownershipRepository.findAll().size();
        // Create the Ownership
        restOwnershipMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ownership)))
            .andExpect(status().isCreated());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeCreate + 1);
        Ownership testOwnership = ownershipList.get(ownershipList.size() - 1);
        assertThat(testOwnership.getLicensePlate()).isEqualTo(DEFAULT_LICENSE_PLATE);
    }

    @Test
    @Transactional
    void createOwnershipWithExistingId() throws Exception {
        // Create the Ownership with an existing ID
        ownership.setId(1L);

        int databaseSizeBeforeCreate = ownershipRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOwnershipMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ownership)))
            .andExpect(status().isBadRequest());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOwnerships() throws Exception {
        // Initialize the database
        ownershipRepository.saveAndFlush(ownership);

        // Get all the ownershipList
        restOwnershipMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ownership.getId().intValue())))
            .andExpect(jsonPath("$.[*].licensePlate").value(hasItem(DEFAULT_LICENSE_PLATE)));
    }

    @Test
    @Transactional
    void getOwnership() throws Exception {
        // Initialize the database
        ownershipRepository.saveAndFlush(ownership);

        // Get the ownership
        restOwnershipMockMvc
            .perform(get(ENTITY_API_URL_ID, ownership.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ownership.getId().intValue()))
            .andExpect(jsonPath("$.licensePlate").value(DEFAULT_LICENSE_PLATE));
    }

    @Test
    @Transactional
    void getNonExistingOwnership() throws Exception {
        // Get the ownership
        restOwnershipMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOwnership() throws Exception {
        // Initialize the database
        ownershipRepository.saveAndFlush(ownership);

        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();

        // Update the ownership
        Ownership updatedOwnership = ownershipRepository.findById(ownership.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOwnership are not directly saved in db
        em.detach(updatedOwnership);
        updatedOwnership.licensePlate(UPDATED_LICENSE_PLATE);

        restOwnershipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOwnership.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOwnership))
            )
            .andExpect(status().isOk());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
        Ownership testOwnership = ownershipList.get(ownershipList.size() - 1);
        assertThat(testOwnership.getLicensePlate()).isEqualTo(UPDATED_LICENSE_PLATE);
    }

    @Test
    @Transactional
    void putNonExistingOwnership() throws Exception {
        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();
        ownership.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOwnershipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ownership.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ownership))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOwnership() throws Exception {
        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();
        ownership.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOwnershipMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ownership))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOwnership() throws Exception {
        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();
        ownership.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOwnershipMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ownership)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOwnershipWithPatch() throws Exception {
        // Initialize the database
        ownershipRepository.saveAndFlush(ownership);

        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();

        // Update the ownership using partial update
        Ownership partialUpdatedOwnership = new Ownership();
        partialUpdatedOwnership.setId(ownership.getId());

        restOwnershipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOwnership.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOwnership))
            )
            .andExpect(status().isOk());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
        Ownership testOwnership = ownershipList.get(ownershipList.size() - 1);
        assertThat(testOwnership.getLicensePlate()).isEqualTo(DEFAULT_LICENSE_PLATE);
    }

    @Test
    @Transactional
    void fullUpdateOwnershipWithPatch() throws Exception {
        // Initialize the database
        ownershipRepository.saveAndFlush(ownership);

        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();

        // Update the ownership using partial update
        Ownership partialUpdatedOwnership = new Ownership();
        partialUpdatedOwnership.setId(ownership.getId());

        partialUpdatedOwnership.licensePlate(UPDATED_LICENSE_PLATE);

        restOwnershipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOwnership.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOwnership))
            )
            .andExpect(status().isOk());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
        Ownership testOwnership = ownershipList.get(ownershipList.size() - 1);
        assertThat(testOwnership.getLicensePlate()).isEqualTo(UPDATED_LICENSE_PLATE);
    }

    @Test
    @Transactional
    void patchNonExistingOwnership() throws Exception {
        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();
        ownership.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOwnershipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ownership.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ownership))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOwnership() throws Exception {
        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();
        ownership.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOwnershipMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ownership))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOwnership() throws Exception {
        int databaseSizeBeforeUpdate = ownershipRepository.findAll().size();
        ownership.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOwnershipMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ownership))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ownership in the database
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOwnership() throws Exception {
        // Initialize the database
        ownershipRepository.saveAndFlush(ownership);

        int databaseSizeBeforeDelete = ownershipRepository.findAll().size();

        // Delete the ownership
        restOwnershipMockMvc
            .perform(delete(ENTITY_API_URL_ID, ownership.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ownership> ownershipList = ownershipRepository.findAll();
        assertThat(ownershipList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
