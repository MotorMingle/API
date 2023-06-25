package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MinglerTest {

    private Mingler mingler1;
    private Mingler mingler2;

    @BeforeEach
    void setUp() {
        mingler1 = new Mingler();
        mingler1.setId("fakeId1");
        mingler1.setEmail("email1@example.com");
        mingler1.setTag("Tag1");
        mingler1.setFirstName("John");
        mingler1.setLastName("Doe");
        mingler1.setBirthDate(LocalDate.now().minusYears(25));
        mingler1.setMinimalAge(20);
        mingler1.setMaximalAge(30);

        mingler2 = new Mingler();
        mingler2.setId("fakeId2");
        mingler2.setEmail("email2@example.com");
        mingler2.setTag("Tag2");
        mingler2.setFirstName("Jane");
        mingler2.setLastName("Doe");
        mingler2.setBirthDate(LocalDate.now().minusYears(30));
        mingler2.setMinimalAge(25);
        mingler2.setMaximalAge(35);
    }

    @Test
    void testEquality() {
        assertNotEquals(mingler1, mingler2);
    }

    @Test
    void testSetterGetter() {
        assertEquals("email1@example.com", mingler1.getEmail());
        assertEquals("Tag1", mingler1.getTag());
        assertEquals("John", mingler1.getFirstName());
        assertEquals("Doe", mingler1.getLastName());
        assertEquals(LocalDate.now().minusYears(25), mingler1.getBirthDate());
        assertEquals(20, mingler1.getMinimalAge());
        assertEquals(30, mingler1.getMaximalAge());

        mingler1.setEmail("newemail@example.com");
        mingler1.setTag("NewTag");
        mingler1.setFirstName("NewFirstName");
        mingler1.setLastName("NewLastName");
        mingler1.setBirthDate(LocalDate.now().minusYears(20));
        mingler1.setMinimalAge(22);
        mingler1.setMaximalAge(33);

        assertEquals("newemail@example.com", mingler1.getEmail());
        assertEquals("NewTag", mingler1.getTag());
        assertEquals("NewFirstName", mingler1.getFirstName());
        assertEquals("NewLastName", mingler1.getLastName());
        assertEquals(LocalDate.now().minusYears(20), mingler1.getBirthDate());
        assertEquals(22, mingler1.getMinimalAge());
        assertEquals(33, mingler1.getMaximalAge());
    }
}
