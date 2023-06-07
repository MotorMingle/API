package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncounterIdTest {

    @Test
    void testEquals() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        EncounterId encounterId1 = new EncounterId();
        encounterId1.setUserId1(userId1);
        encounterId1.setUserId2(userId2);

        EncounterId encounterId2 = new EncounterId();
        encounterId2.setUserId1(userId1);
        encounterId2.setUserId2(userId2);

        assertEquals(encounterId1, encounterId2);

        encounterId2.setUserId2(UUID.randomUUID());
        assertNotEquals(encounterId1, encounterId2);
    }

    @Test
    void testHashCode() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        EncounterId encounterId1 = new EncounterId();
        encounterId1.setUserId1(userId1);
        encounterId1.setUserId2(userId2);

        EncounterId encounterId2 = new EncounterId();
        encounterId2.setUserId1(userId1);
        encounterId2.setUserId2(userId2);

        assertEquals(encounterId1.hashCode(), encounterId2.hashCode());

        encounterId2.setUserId2(UUID.randomUUID());
        assertNotEquals(encounterId1.hashCode(), encounterId2.hashCode());
    }
}
