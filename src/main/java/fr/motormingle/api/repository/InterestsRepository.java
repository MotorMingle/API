package fr.motormingle.api.repository;

import fr.motormingle.api.domain.Interests;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Interests entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterestsRepository extends JpaRepository<Interests, Long> {}
