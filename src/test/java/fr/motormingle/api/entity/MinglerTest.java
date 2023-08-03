package fr.motormingle.api.entity;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void testToMinglerTagGet() {
        assertEquals(new MinglerTagGet("Tag1"), mingler1.toMinglerTagGet());
    }

    @Test
    void testNoArgsConstructor() {
        Mingler mingler = new Mingler();
        assertNotNull(mingler);
    }

    @Test
    void testRequiredArgsConstructorNull() {
        String id = "fakeId";
        String email = "email@example.com";
        String tag = "Tag";
        String firstName = "John";
        String lastName = "Doe";
        assertThrows(NullPointerException.class, () -> new Mingler(null, null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Mingler(id, null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Mingler(null, email, null, null, null));
        assertThrows(NullPointerException.class, () -> new Mingler(null, null, tag, null, null));
        assertThrows(NullPointerException.class, () -> new Mingler(null, null, null, firstName, null));
        assertThrows(NullPointerException.class, () -> new Mingler(null, null, null, null, lastName));
    }
}
