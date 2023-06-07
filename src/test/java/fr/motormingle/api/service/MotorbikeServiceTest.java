package fr.motormingle.api.service;

import fr.motormingle.api.entity.Manufacturer;
import fr.motormingle.api.entity.Motorbike;
import fr.motormingle.api.exception.NotFoundException;
import fr.motormingle.api.repository.MotorbikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class MotorbikeServiceTest {

    @InjectMocks
    private MotorbikeService motorbikeService;

    @Mock
    private MotorbikeRepository motorbikeRepository;

    private Motorbike motorbike;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        motorbike = new Motorbike();
        motorbike.setId(1L);
        motorbike.setModel("Model Y");
        motorbike.setYear(2020);
        motorbike.setHorsePower(320);
        Manufacturer manufacturer = new Manufacturer(); // Make sure you have a Manufacturer class defined.
        manufacturer.setId(1L);
        motorbike.setManufacturer(manufacturer);
    }

    @Test
    void testFindAll() {
        when(motorbikeRepository.findAll()).thenReturn(Collections.singletonList(motorbike));

        List<Motorbike> result = motorbikeService.findAll();
        assertEquals(1, result.size());
        assertEquals(motorbike, result.get(0));
    }

    @Test
    void testFindById() {
        when(motorbikeRepository.findById(anyLong())).thenReturn(Optional.of(motorbike));

        Motorbike result = motorbikeService.findById(1L);
        assertEquals(motorbike, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(motorbikeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> motorbikeService.findById(2L));
    }
}
