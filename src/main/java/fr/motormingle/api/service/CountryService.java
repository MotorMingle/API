package fr.motormingle.api.service;

import fr.motormingle.api.entity.Country;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements FindService<Country, String> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(String id) {
        return countryRepository.findById(id).orElseThrow(() -> new NotFoundException("Country with id " + id + " was not found"));
    }
}
