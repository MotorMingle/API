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
        User user = new User();
        user.setId(uuid);
        position.setUser(user);

        LocalDateTime now = LocalDateTime.now();
        PositionId positionId = new PositionId();
        positionId.setUserId(uuid);
        positionId.setDate(now);
        position.setId(positionId);

        position.setLatitude(12.34);
        position.setLongitude(56.78);

        assertEquals(user, position.getUser());
        assertEquals(positionId, position.getId());
        assertEquals(12.34, position.getLatitude());
        assertEquals(56.78, position.getLongitude());
    }
}