package fr.motormingle.api.service;

import fr.motormingle.api.entity.Car;
import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.entity.Ownership;
import fr.motormingle.api.entity.Vehicle;
import fr.motormingle.api.repository.OwnershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OwnershipServiceTest {

    @InjectMocks
    private OwnershipService ownershipService;

    @Mock
    private OwnershipRepository ownershipRepository;

    private Ownership ownership;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        Mingler mingler = new Mingler();
        mingler.setId(UUID.randomUUID());
        mingler.setEmail("test@test.com");

        Vehicle vehicle = new Car();
        vehicle.setId(1L);

        ownership = new Ownership();
        ownership.setMingler(mingler);
        ownership.setVehicle(vehicle);
    }

    @Test
    void testFindAll() {
        when(ownershipRepository.findAll()).thenReturn(Collections.singletonList(ownership));

        List<Ownership> result = ownershipService.findAll();
        assertEquals(1, result.size());
        assertEquals(ownership, result.get(0));
    }

    @Test
    void testFindById() {
        when(ownershipRepository.findById(any())).thenReturn(Optional.of(ownership));

        Ownership result = ownershipService.findById(ownership.getId());
        assertEquals(ownership, result);
    }
}
