package fr.motormingle.api.service;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import fr.motormingle.api.entity.Manufacturer;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.ManufacturerRepository;
import fr.motormingle.api.service.stereotype.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService implements FindService<Manufacturer, Long> {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) throws NotFoundException {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manufacturer with id " + id + " was not found"));
    }

    public List<ManufacturerItemGet> getManufacturerItemList() {
        return manufacturerRepository.findAll().stream().map(Manufacturer::toManufacturerItemGet).toList();
    }
}
