package fr.motormingle.api.entity;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MinglerTest {

    private Mingler mingler1;
    private Mingler mingler2;

    @BeforeEach
    void setUp() {
        mingler1 = new Mingler("fakeId1", "email1@example.com", "Tag1", "John", "Doe");
        mingler2 = new Mingler("fakeId2", "email2@example.com", "Tag2", "Jane", "Doe");
    }

    @Test
    void testEquality() {
        assertNotEquals(mingler1, mingler2);
    }

    @Test
    void testSetterGetter() {
        mingler1.setId("fakeId3");
        assertEquals("fakeId3", mingler1.getId());
        mingler1.setEmail("email3@example.com");
        assertEquals("email3@example.com", mingler1.getEmail());
        mingler1.setTag("Tag3");
        assertEquals("Tag3", mingler1.getTag());
        mingler1.setFirstName("Jack");
        assertEquals("Jack", mingler1.getFirstName());
        mingler1.setLastName("Does");
        assertEquals("Does", mingler1.getLastName());
        mingler1.setBirthDate(LocalDate.of(2000, 1, 1));
        assertEquals(LocalDate.of(2000, 1, 1), mingler1.getBirthDate());
        mingler1.setMinimalAge(18);
        assertEquals(18, mingler1.getMinimalAge());
        mingler1.setMaximalAge(99);
        assertEquals(99, mingler1.getMaximalAge());
    }

    @Test
    void testIdNull() {
        assertThrows(NullPointerException.class, () -> mingler1.setId(null));
    }

    @Test
    void testEmailNull() {
        assertThrows(NullPointerException.class, () -> mingler1.setEmail(null));
    }

    @Test
    void testTagNull() {
        assertThrows(NullPointerException.class, () -> mingler1.setTag(null));
    }

    @Test
    void testFirstNameNull() {
        assertThrows(NullPointerException.class, () -> mingler1.setFirstName(null));
    }

    @Test
    void testLastNameNull() {
        assertThrows(NullPointerException.class, () -> mingler1.setLastName(null));
    }

    @Test
    void testBirthDateNull() {
        mingler1.setBirthDate(null);
        assertNull(mingler1.getBirthDate());
    }

    @Test
    void testMinimalAgeNull() {
        mingler1.setMinimalAge(null);
        assertNull(mingler1.getMinimalAge());
    }

    @Test
    void testMaximalAgeNull() {
        mingler1.setMaximalAge(null);
        assertNull(mingler1.getMaximalAge());
    }

    @Test
    void testRequiredArgsConstructor() {
        String id = "fakeId";
        String email = "email@example.com";
        String tag = "Tag";
        String firstName = "John";
        String lastName = "Doe";
        Mingler mingler = new Mingler(id, email, tag, firstName, lastName);
        assertNotNull(mingler);
        assertEquals(id, mingler.getId());
        assertEquals(email, mingler.getEmail());
        assertEquals(tag, mingler.getTag());
        assertEquals(firstName, mingler.getFirstName());
        assertEquals(lastName, mingler.getLastName());
    }

    @Test
    void testToMinglerTagGet() {
        assertEquals(new MinglerTagGet("Tag1"), mingler1.toMinglerTagGet());
    }

    @Test
    void testNoArgsConstructor() {
        Mingler mingler = new Mingler();
        assertNotNull(mingler);
    }

    @ParameterizedTest
    @MethodSource("requiredArgsConstructorNullArguments")
    void testRequiredArgsConstructorNull(String id, String email, String tag, String firstName, String lastName) {
        assertThrows(NullPointerException.class, () -> new Mingler(id, email, tag, firstName, lastName));
    }

    static Stream<Arguments> requiredArgsConstructorNullArguments() {
        String id = "fakeId";
        String email = "email@example.com";
        String tag = "Tag";
        String firstName = "John";
        String lastName = "Doe";

        return Stream.of(
                Arguments.of(null, null, null, null, null),
                Arguments.of(id, null, null, null, null),
                Arguments.of(null, email, null, null, null),
                Arguments.of(null, null, tag, null, null),
                Arguments.of(null, null, null, firstName, null),
                Arguments.of(null, null, null, null, lastName),
                Arguments.of(id, email, null, null, null),
                Arguments.of(id, null, tag, null, null),
                Arguments.of(id, null, null, firstName, null),
                Arguments.of(id, null, null, null, lastName),
                Arguments.of(null, email, tag, null, null),
                Arguments.of(null, email, null, firstName, null),
                Arguments.of(null, email, null, null, lastName),
                Arguments.of(null, null, tag, firstName, null),
                Arguments.of(null, null, tag, null, lastName),
                Arguments.of(null, null, null, firstName, lastName),
                Arguments.of(id, email, tag, null, null),
                Arguments.of(id, email, null, firstName, null),
                Arguments.of(id, email, null, null, lastName),
                Arguments.of(id, null, tag, firstName, null),
                Arguments.of(id, null, tag, null, lastName),
                Arguments.of(id, null, null, firstName, lastName),
                Arguments.of(null, email, tag, firstName, null),
                Arguments.of(null, email, tag, null, lastName),
                Arguments.of(null, email, null, firstName, lastName),
                Arguments.of(id, null, null, firstName, lastName),
                Arguments.of(id, email, tag, firstName, null),
                Arguments.of(id, email, tag, null, lastName),
                Arguments.of(id, email, null, firstName, lastName),
                Arguments.of(id, null, tag, firstName, lastName),
                Arguments.of(null, email, tag, firstName, lastName)
        );
    }
}
