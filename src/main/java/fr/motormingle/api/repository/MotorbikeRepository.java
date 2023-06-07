package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotorbikeRepository extends JpaRepository<Motorbike, Long>, JpaSpecificationExecutor<Motorbike> {
}