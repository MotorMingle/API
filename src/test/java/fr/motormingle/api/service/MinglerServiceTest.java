package fr.motormingle.api.service;

import fr.motormingle.api.entity.Mingler;
import fr.motormingle.api.repository.MinglerRepository;
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

class MinglerServiceTest {

    @InjectMocks
    private MinglerService minglerService;

    @Mock
    private MinglerRepository minglerRepository;

    private Mingler mingler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mingler = new Mingler("5d4f9dc62d66", "lebarbanchon.valentin@gmail.com", "enixo", "Valentin", "Lebarbanchon");
    }

    @Test
    void testFindAll() {
        when(minglerRepository.findAll()).thenReturn(Collections.singletonList(mingler));

        List<Mingler> result = minglerService.findAll();
        assertEquals(1, result.size());
        assertEquals(mingler, result.get(0));
    }

    @Test
    void testFindById() {
        when(minglerRepository.findById(any(String.class))).thenReturn(Optional.of(mingler));

        Mingler result = minglerService.findById(mingler.getId());
        assertEquals(mingler, result);
    }
}
