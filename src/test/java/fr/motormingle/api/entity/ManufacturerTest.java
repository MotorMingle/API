package fr.motormingle.api.entity;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerTest {

    private Manufacturer suzuki;

    @BeforeEach
    public void setUp() {
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
    void testIdNull() {
        assertThrows(NullPointerException.class, () -> suzuki.setId(null));
    }

    @Test
    void testNameNull() {
        assertThrows(NullPointerException.class, () -> suzuki.setName(null));
    }

    @Test
    void testCountryNull() {
        assertThrows(NullPointerException.class, () -> suzuki.setCountry(null));
    }

    @Test
    void testRequiredArgsConstructor() {
        Long id = 1L;
        String name = "Suzuki";
        Country country = new Country("JPN", "Japan");
        Manufacturer manufacturer = new Manufacturer(id, name, country);
        assertNotNull(manufacturer);
        assertEquals(id, manufacturer.getId());
        assertEquals(name, manufacturer.getName());
        assertEquals(country, manufacturer.getCountry());
    }
    
    @Test
    void testToManufacturerItemGet() {
        assertEquals(new ManufacturerItemGet(1L, "Suzuki"), suzuki.toManufacturerItemGet());
    }

    @Test
    void testRequiredArgsConstructorNull() {
        Long id = 1L;
        String name = "Suzuki";
        Country country = new Country("JPN", "Japan");
        assertThrows(NullPointerException.class, () -> new Manufacturer(null, null, null));
        assertThrows(NullPointerException.class, () -> new Manufacturer(id, null, null));
        assertThrows(NullPointerException.class, () -> new Manufacturer(null, name, null));
        assertThrows(NullPointerException.class, () -> new Manufacturer(null, null, country));
        assertThrows(NullPointerException.class, () -> new Manufacturer(id, name, null));
        assertThrows(NullPointerException.class, () -> new Manufacturer(id, null, country));
        assertThrows(NullPointerException.class, () -> new Manufacturer(null, name, country));
    }
}
