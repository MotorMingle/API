package fr.motormingle.api.repository;

import fr.motormingle.api.domain.Ownership;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ownership entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {}
