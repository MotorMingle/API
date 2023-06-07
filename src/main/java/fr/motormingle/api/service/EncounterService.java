package fr.motormingle.api.service;

import fr.motormingle.api.entity.Encounter;
import fr.motormingle.api.entity.EncounterId;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.EncounterRepository;
import fr.motormingle.api.service.stereotype.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncounterService implements FindService<Encounter, EncounterId> {

    @Autowired
    private EncounterRepository encounterRepository;

    @Override
    public List<Encounter> findAll() {
        return encounterRepository.findAll();
    }

    @Override
    public Encounter findById(EncounterId id) throws NotFoundException {
        return encounterRepository.findById(id).orElseThrow(() -> new NotFoundException("Encounter not found"));
    }
}
