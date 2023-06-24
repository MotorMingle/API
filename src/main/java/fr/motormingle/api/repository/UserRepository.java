package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Mingler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<Mingler, String>, JpaSpecificationExecutor<Mingler> {
}