package fr.motormingle.api.service;

import fr.motormingle.api.entity.Ownership;
import fr.motormingle.api.entity.OwnershipId;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.OwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnershipService implements FindService<Ownership, OwnershipId> {

    @Autowired
    private OwnershipRepository ownershipRepository;

    @Override
    public List<Ownership> findAll() {
        return ownershipRepository.findAll();
    }

    @Override
    public Ownership findById(OwnershipId id) throws NotFoundException {
        return ownershipRepository.findById(id).orElseThrow(() -> new NotFoundException("Ownership not found"));
    }
}
