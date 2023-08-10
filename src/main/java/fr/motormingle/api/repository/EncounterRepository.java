package fr.motormingle.api.repository;

import fr.motormingle.api.domain.Encounter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Encounter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {}
