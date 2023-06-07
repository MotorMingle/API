package fr.motormingle.api.service;

import fr.motormingle.api.entity.Encounter;
import fr.motormingle.api.entity.EncounterId;
import fr.motormingle.api.entity.EncounterStatus;
import fr.motormingle.api.repository.EncounterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EncounterServiceTest {

    @InjectMocks
    private EncounterService encounterService;

    @Mock
    private EncounterRepository encounterRepository;

    private Encounter encounter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        EncounterId encounterId = new EncounterId();
        encounterId.setUserId1(UUID.randomUUID());
        encounterId.setUserId2(UUID.randomUUID());

        encounter = new Encounter();
        encounter.setId(encounterId);
        encounter.setDate(LocalDate.now());
        encounter.setCount(1);
        encounter.setHash("hash");
        encounter.setStatus(EncounterStatus.PENDING);
    }

    @Test
    void testFindAll() {
        when(encounterRepository.findAll()).thenReturn(Collections.singletonList(encounter));

        List<Encounter> result = encounterService.findAll();
        assertEquals(1, result.size());
        assertEquals(encounter, result.get(0));
    }

    @Test
    void testFindById() {
        when(encounterRepository.findById(any(EncounterId.class))).thenReturn(Optional.of(encounter));

        Encounter result = encounterService.findById(encounter.getId());
        assertEquals(encounter, result);
    }
}
