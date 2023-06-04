package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhotoRepository extends JpaRepository<Photo, Integer>, JpaSpecificationExecutor<Photo> {
}