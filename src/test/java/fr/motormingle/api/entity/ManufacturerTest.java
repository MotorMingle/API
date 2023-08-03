package fr.motormingle.api.entity;

import fr.motormingle.api.dto.manufacturer.get.ManufacturerItemGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("requiredArgsConstructorNullArguments")
    void testRequiredArgsConstructorNull(Long id, String name, Country country) {
        assertThrows(NullPointerException.class, () -> new Manufacturer(id, name, country));
    }

    static Stream<Arguments> requiredArgsConstructorNullArguments() {
        Long id = 1L;
        String name = "Suzuki";
        Country country = new Country("JPN", "Japan");

        return Stream.of(
                Arguments.of(null, null, null),
                Arguments.of(id, null, null),
                Arguments.of(null, name, null),
                Arguments.of(null, null, country),
                Arguments.of(null, name, country),
                Arguments.of(id, null, country),
                Arguments.of(id, name, null)
        );
    }
}
