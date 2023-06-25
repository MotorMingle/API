package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void testGetterAndSetter() {
        Position position = new Position();

        Mingler mingler = new Mingler();
        mingler.setId("fakeId");
        position.setMingler(mingler);

        LocalDateTime now = LocalDateTime.now();
        PositionId positionId = new PositionId();
        positionId.setUserId("fakeId");
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