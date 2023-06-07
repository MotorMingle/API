package fr.motormingle.api.repository;

import fr.motormingle.api.entity.Photo;
import fr.motormingle.api.entity.PhotoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhotoRepository extends JpaRepository<Photo, PhotoId>, JpaSpecificationExecutor<Photo> {
}