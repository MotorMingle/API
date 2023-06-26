package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionIdTest {

    @Test
    void testEqualsAndHashCode() {
        String userId1 = "userId1";
        String userId2 = "userId2";

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime later = now.plusHours(1);

        PositionId positionId1 = new PositionId();
        positionId1.setUserId(userId1);
        positionId1.setDate(now);

        PositionId positionId2 = new PositionId();
        positionId2.setUserId(userId1);
        positionId2.setDate(now);

        PositionId positionId3 = new PositionId();
        positionId3.setUserId(userId2);
        positionId3.setDate(now);

        PositionId positionId4 = new PositionId();
        positionId4.setUserId(userId1);
        positionId4.setDate(later);

        PositionId positionId5 = new PositionId();
        positionId5.setUserId(null);
        positionId5.setDate(now);

        PositionId positionId6 = new PositionId();
        positionId6.setUserId(userId1);
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
