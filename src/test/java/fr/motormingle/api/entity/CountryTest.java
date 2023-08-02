package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest {

    private Country japan;

    @BeforeEach
    void setUp() {
        japan = new Country("JPN", "Japan");
    }
    
    @Test
    void testSetterAndGetter() {
        japan.setId("ITA");
        assertEquals("ITA", japan.getId());
        japan.setName("Italy");
        assertEquals("Italy", japan.getName());
    }
}
