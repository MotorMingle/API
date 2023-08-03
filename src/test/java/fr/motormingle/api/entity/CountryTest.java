package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    private Country country;

    @BeforeEach
    public void setUp() {
        country = new Country();
    }

    @Test
    void testId() {
        String id = "USA";
        country.setId(id);
        assertEquals(id, country.getId());
    }

    @Test
    void testIdNull() {
        assertThrows(NullPointerException.class, () -> country.setId(null));
    }

    @Test
    void testName() {
        String name = "United States";
        country.setName(name);
        assertEquals(name, country.getName());
    }

    @Test
    void testNameNull() {
        assertThrows(NullPointerException.class, () -> country.setName(null));
    }

    @Test
    void testNoArgsConstructor() {
        Country country = new Country();
        assertNotNull(country);
    }

    @Test
    void testRequiredArgsConstructor() {
        String id = "USA";
        String name = "United States";
        Country country = new Country(id, name);
        assertNotNull(country);
        assertEquals(id, country.getId());
        assertEquals(name, country.getName());
    }

    @Test
    void testRequiredArgsConstructorNull() {
        String id = "USA";
        String name = "United States";
        assertThrows(NullPointerException.class, () -> new Country(null, null));
        assertThrows(NullPointerException.class, () -> new Country(id, null));
        assertThrows(NullPointerException.class, () -> new Country(null, name));
    }
}