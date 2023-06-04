package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Ownership;
import fr.motormingle.api.entity.OwnershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OwnershipRepository extends JpaRepository<Ownership, OwnershipId>, JpaSpecificationExecutor<Ownership> {
}