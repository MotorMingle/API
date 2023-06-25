package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PositionIdTest {

    @Test
    void testEqualsAndHashCode() {
        PositionId positionId1 = new PositionId();
        positionId1.setUserId("User1");
        positionId1.setDate(LocalDateTime.now());

        PositionId positionId2 = new PositionId();
        positionId2.setUserId("User1");
        positionId2.setDate(LocalDateTime.now());

        assertEquals(positionId1, positionId2);
        assertEquals(positionId1.hashCode(), positionId2.hashCode());

        // Changing user
        positionId2.setUserId("User2");
        assertNotEquals(positionId1, positionId2);
        assertNotEquals(positionId1.hashCode(), positionId2.hashCode());

        // Changing date
        positionId2.setUserId("User1");
        positionId2.setDate(LocalDateTime.now().minusDays(1));
        assertNotEquals(positionId1, positionId2);
        assertNotEquals(positionId1.hashCode(), positionId2.hashCode());
    }
}
