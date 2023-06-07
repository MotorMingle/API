package fr.motormingle.api.service;

import fr.motormingle.api.entity.Photo;
import fr.motormingle.api.entity.PhotoId;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.PhotoRepository;
import fr.motormingle.api.service.stereotype.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService implements FindService<Photo, PhotoId> {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo findById(PhotoId id) throws NotFoundException {
        return photoRepository.findById(id).orElseThrow(() -> new NotFoundException("Photo not found"));
    }
}
