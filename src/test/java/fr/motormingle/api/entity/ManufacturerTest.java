package fr.motormingle.api.entity;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManufacturerTest {

    private Manufacturer suzuki;

    @BeforeEach
    void setUp() {
        suzuki = new Manufacturer(1L, "Suzuki", new Country("JPN", "Japan"));
    }
    
    @Test
    void testSetterAndGetter() {
        suzuki.setId(2L);
        assertEquals(2L, suzuki.getId());
        suzuki.setName("Ducati");
        assertEquals("Ducati", suzuki.getName());
        Country italy = new Country("ITA", "Italy");
        suzuki.setCountry(italy);
        assertEquals(italy, suzuki.getCountry());
    }
    
    @Test
    void testToManufacturerItemGet() {
        assertEquals(new ManufacturerItemGet(1L, "Suzuki"), suzuki.toManufacturerItemGet());
    }
}
