package fr.motormingle.api.repository;

import fr.motormingle.api.entity.MotorbikeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotorbikeTypeRepository extends JpaRepository<MotorbikeType, Integer>, JpaSpecificationExecutor<MotorbikeType> {
}