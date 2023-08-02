package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountryTest {

    private Country country;

    @BeforeEach
    public void setUp() {
        country = new Country();
    }

    @Test
    public void testId() {
        String id = "USA";
        country.setId(id);
        assertEquals(id, country.getId());
    }

    @Test
    public void testIdNull() {
        assertThrows(NullPointerException.class, () -> country.setId(null));
    }

    @Test
    public void testName() {
        String name = "United States";
        country.setName(name);
        assertEquals(name, country.getName());
    }

    @Test
    public void testNameNull() {
        assertThrows(NullPointerException.class, () -> country.setName(null));
    }

    @Test
    public void testNoArgsConstructor() {
        Country country = new Country();
        assertNotNull(country);
    }

    @Test
    public void testRequiredArgsConstructor() {
        String id = "USA";
        String name = "United States";
        Country country = new Country(id, name);
        assertNotNull(country);
        assertEquals(id, country.getId());
        assertEquals(name, country.getName());
    }

    @Test
    public void testRequiredArgsConstructorNull() {
        String id = "USA";
        String name = "United States";
        assertThrows(NullPointerException.class, () -> new Country(null, null));
        assertThrows(NullPointerException.class, () -> new Country(id, null));
        assertThrows(NullPointerException.class, () -> new Country(null, name));
    }
}