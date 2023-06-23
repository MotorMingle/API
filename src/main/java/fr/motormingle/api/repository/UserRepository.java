package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Mingler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Mingler, UUID>, JpaSpecificationExecutor<Mingler> {
    Optional<Mingler> findByEmailIgnoreCase(@NonNull String email);
}