package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Encounter;
import fr.motormingle.api.entity.EncounterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EncounterRepository extends JpaRepository<Encounter, EncounterId>, JpaSpecificationExecutor<Encounter> {
}