package fr.motormingle.api.repositories;

import fr.motormingle.api.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}
