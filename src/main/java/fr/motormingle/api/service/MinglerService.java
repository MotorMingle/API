package fr.motormingle.api.service;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.MinglerRepository;
import fr.motormingle.api.service.stereotype.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinglerService implements FindService<Mingler, String> {

    @Autowired
    private MinglerRepository minglerRepository;

    @Override
    public List<Mingler> findAll() {
        return minglerRepository.findAll();
    }

    @Override
    public Mingler findById(String id) throws NotFoundException {
        return minglerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mingler with id " + id + " was not found"));
    }

    public MinglerTagGet findTagById(String id) throws NotFoundException {
        return findById(id).toMinglerTagGet();
    }
}
