package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionIdTest {

    @Test
    void testEqualsAndHashCode() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plusHours(1);

        PositionId positionId1 = new PositionId();
        positionId1.setUserId(uuid1);
        positionId1.setDate(now);

        PositionId positionId2 = new PositionId();
        positionId2.setUserId(uuid1);
        positionId2.setDate(now);

        PositionId positionId3 = new PositionId();
        positionId3.setUserId(uuid2);
        positionId3.setDate(now);

        PositionId positionId4 = new PositionId();
        positionId4.setUserId(uuid1);
        positionId4.setDate(later);

        PositionId positionId5 = new PositionId();
        positionId5.setUserId(null);
        positionId5.setDate(now);

        PositionId positionId6 = new PositionId();
        positionId6.setUserId(uuid1);
        positionId6.setDate(null);

        // Testing equals
        assertEquals(positionId1, positionId2);
        assertNotEquals(positionId1, positionId3);
        assertNotEquals(positionId1, positionId4);
        assertNotEquals(positionId1, positionId5);
        assertNotEquals(positionId1, positionId6);

        // Testing hashcode
        assertEquals(positionId1.hashCode(), positionId2.hashCode());
        assertNotEquals(positionId1.hashCode(), positionId3.hashCode());
        assertNotEquals(positionId1.hashCode(), positionId4.hashCode());
        assertNotEquals(positionId1.hashCode(), positionId5.hashCode());
        assertNotEquals(positionId1.hashCode(), positionId6.hashCode());

        // Testing equals with the same instance
        assertEquals(positionId1, positionId1);

        // Testing equals with null
        assertNotEquals(null, positionId1);

        // Testing equals with a different class
        assertNotEquals(positionId1, new Object());
    }
}
