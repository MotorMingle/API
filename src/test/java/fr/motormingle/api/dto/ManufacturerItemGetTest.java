package fr.motormingle.api.dto;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testRequiredArgsConstructor() {
        Long id = 1L;
        String name = "Suzuki";
        ManufacturerItemGet manufacturerItemGet = new ManufacturerItemGet(id, name);
        assertNotNull(manufacturerItemGet);
        assertEquals(id, manufacturerItemGet.getId());
        assertEquals(name, manufacturerItemGet.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        ManufacturerItemGet manufacturerItemGet1 = new ManufacturerItemGet(1L, "Suzuki");
        ManufacturerItemGet manufacturerItemGet2 = new ManufacturerItemGet(1L, "Suzuki");
        assertEquals(manufacturerItemGet1, manufacturerItemGet2);
        assertEquals(manufacturerItemGet1.hashCode(), manufacturerItemGet2.hashCode());
    }

    @Test
    void testIdNull() {
        assertThrows(NullPointerException.class, () -> manufacturerItemGet.setId(null));
    }

    @Test
    void testNameNull() {
        assertThrows(NullPointerException.class, () -> manufacturerItemGet.setName(null));
    }

    @Test
    void testRequiredArgsConstructorNull() {
        assertThrows(NullPointerException.class, () -> new ManufacturerItemGet(null, "Suzuki"));
        assertThrows(NullPointerException.class, () -> new ManufacturerItemGet(1L, null));
    }
}
