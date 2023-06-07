package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setEmail("email1@example.com");
        user1.setTag("Tag1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setBirthDate(LocalDate.now().minusYears(25));
        user1.setMinimalAge(20);
        user1.setMaximalAge(30);

        user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setEmail("email2@example.com");
        user2.setTag("Tag2");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setBirthDate(LocalDate.now().minusYears(30));
        user2.setMinimalAge(25);
        user2.setMaximalAge(35);
    }

    @Test
    void testEquality() {
        assertNotEquals(user1, user2);
    }

    @Test
    void testSetterGetter() {
        assertEquals("email1@example.com", user1.getEmail());
        assertEquals("Tag1", user1.getTag());
        assertEquals("John", user1.getFirstName());
        assertEquals("Doe", user1.getLastName());
        assertEquals(LocalDate.now().minusYears(25), user1.getBirthDate());
        assertEquals(20, user1.getMinimalAge());
        assertEquals(30, user1.getMaximalAge());

        user1.setEmail("newemail@example.com");
        user1.setTag("NewTag");
        user1.setFirstName("NewFirstName");
        user1.setLastName("NewLastName");
        user1.setBirthDate(LocalDate.now().minusYears(20));
        user1.setMinimalAge(22);
        user1.setMaximalAge(33);

        assertEquals("newemail@example.com", user1.getEmail());
        assertEquals("NewTag", user1.getTag());
        assertEquals("NewFirstName", user1.getFirstName());
        assertEquals("NewLastName", user1.getLastName());
        assertEquals(LocalDate.now().minusYears(20), user1.getBirthDate());
        assertEquals(22, user1.getMinimalAge());
        assertEquals(33, user1.getMaximalAge());
    }
}
