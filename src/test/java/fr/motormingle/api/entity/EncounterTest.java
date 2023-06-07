package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncounterTest {

    private Encounter encounter1;
    private Encounter encounter2;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setEmail("email1@example.com");

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setEmail("email2@example.com");

        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setEmail("email3@example.com");

        encounter1 = new Encounter();
        EncounterId encounterId1 = new EncounterId();
        encounterId1.setUserId1(user1.getId());
        encounterId1.setUserId2(user2.getId());
        encounter1.setId(encounterId1);
        encounter1.setUserId1(user1);
        encounter1.setUserId2(user2);
        encounter1.setHash("hash1");
        encounter1.setCount(1);
        encounter1.setDate(LocalDate.now());
        encounter1.setStatus(EncounterStatus.ACCEPTED);

        encounter2 = new Encounter();
        EncounterId encounterId2 = new EncounterId();
        encounterId2.setUserId1(user1.getId());
        encounterId2.setUserId2(user3.getId());
        encounter2.setId(encounterId2);
        encounter2.setUserId1(user1);
        encounter2.setUserId2(user3);
        encounter2.setHash("hash2");
        encounter2.setCount(2);
        encounter2.setDate(LocalDate.now().plusDays(1));
        encounter2.setStatus(EncounterStatus.DECLINED);
    }

    @Test
    void testEquality() {
        assertNotEquals(encounter1, encounter2);
    }

    @Test
    void testSetterGetter() {
        assertEquals("hash1", encounter1.getHash());
        assertEquals(1, encounter1.getCount());
        assertEquals(LocalDate.now(), encounter1.getDate());
        assertEquals(EncounterStatus.ACCEPTED, encounter1.getStatus());

        encounter1.setHash("newhash");
        encounter1.setCount(3);
        encounter1.setDate(LocalDate.now().plusDays(2));
        encounter1.setStatus(EncounterStatus.PENDING);

        assertEquals("newhash", encounter1.getHash());
        assertEquals(3, encounter1.getCount());
        assertEquals(LocalDate.now().plusDays(2), encounter1.getDate());
        assertEquals(EncounterStatus.PENDING, encounter1.getStatus());
    }
}
