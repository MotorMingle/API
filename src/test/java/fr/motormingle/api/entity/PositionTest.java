package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void testGetterAndSetter() {
        Position position = new Position();

        UUID uuid = UUID.randomUUID();
        Mingler mingler = new Mingler();
        mingler.setId(uuid);
        position.setMingler(mingler);

        LocalDateTime now = LocalDateTime.now();
        PositionId positionId = new PositionId();
        positionId.setUserId(uuid);
        positionId.setDate(now);
        position.setId(positionId);

        position.setLatitude(12.34);
        position.setLongitude(56.78);

        assertEquals(mingler, position.getMingler());
        assertEquals(positionId, position.getId());
        assertEquals(12.34, position.getLatitude());
        assertEquals(56.78, position.getLongitude());
    }
}