package fr.motormingle.api.dto;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManufacturerItemGetTest {
    private ManufacturerItemGet manufacturerItemGet;

    @BeforeEach
    public void setUp() {
        manufacturerItemGet = new ManufacturerItemGet(1L, "Suzuki");
    }

    @Test
    void testSetterAndGetter() {
        manufacturerItemGet.setId(2L);
        assertEquals(2L, manufacturerItemGet.getId());
        manufacturerItemGet.setName("Suzuki");
        assertEquals("Suzuki", manufacturerItemGet.getName());
    }
}
