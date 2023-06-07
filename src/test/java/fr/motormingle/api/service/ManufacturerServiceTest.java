package fr.motormingle.api.service;

import fr.motormingle.api.entity.Manufacturer;
import fr.motormingle.api.repository.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ManufacturerServiceTest {

    @InjectMocks
    private ManufacturerService manufacturerService;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    private Manufacturer manufacturer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("Test Manufacturer");
    }

    @Test
    void testFindAll() {
        when(manufacturerRepository.findAll()).thenReturn(Collections.singletonList(manufacturer));

        List<Manufacturer> result = manufacturerService.findAll();
        assertEquals(1, result.size());
        assertEquals(manufacturer, result.get(0));
    }

    @Test
    void testFindById() {
        when(manufacturerRepository.findById(any(Long.class))).thenReturn(Optional.of(manufacturer));

        Manufacturer result = manufacturerService.findById(manufacturer.getId());
        assertEquals(manufacturer, result);
    }
}
