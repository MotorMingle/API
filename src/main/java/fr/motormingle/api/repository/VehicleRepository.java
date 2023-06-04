package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>, JpaSpecificationExecutor<Vehicle> {
}