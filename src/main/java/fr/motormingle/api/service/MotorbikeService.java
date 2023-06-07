package fr.motormingle.api.service;

import fr.motormingle.api.entity.Motorbike;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.MotorbikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorbikeService implements FindService<Motorbike, Long> {

    @Autowired
    private MotorbikeRepository motorbikeRepository;

    public List<Motorbike> findAll() {
        return motorbikeRepository.findAll();
    }

    public Motorbike findById(Long id) {
        return motorbikeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Motorbike with id " + id + " was not found"));
    }
}
