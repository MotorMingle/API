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

        PositionId positionId1 = new PositionId();
        positionId1.setUserId(uuid1);
        positionId1.setDate(now);

        PositionId positionId2 = new PositionId();
        positionId2.setUserId(uuid1);
        positionId2.setDate(now);

        PositionId positionId3 = new PositionId();
        positionId3.setUserId(uuid2);
        positionId3.setDate(now);

        assertEquals(positionId1, positionId2);
        assertEquals(positionId1.hashCode(), positionId2.hashCode());

        assertNotEquals(positionId1, positionId3);
        assertNotEquals(positionId1.hashCode(), positionId3.hashCode());
    }
}