package fr.motormingle.api.service;

import fr.motormingle.api.entity.Country;
import fr.motormingle.api.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository countryRepository;

    private Country country;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        country = new Country();
        country.setId("FR");
        country.setName("France");
    }

    @Test
    void testFindAll() {
        when(countryRepository.findAll()).thenReturn(Collections.singletonList(country));

        List<Country> result = countryService.findAll();
        assertEquals(1, result.size());
        assertEquals(country, result.get(0));
    }

    @Test
    void testFindById() {
        when(countryRepository.findById(anyString())).thenReturn(Optional.of(country));

        Country result = countryService.findById(country.getId());
        assertEquals(country, result);
    }
}
