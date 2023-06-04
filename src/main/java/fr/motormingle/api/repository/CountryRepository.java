package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {
}