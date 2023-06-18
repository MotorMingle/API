package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserPairIdTest {

    private UUID userId1;
    private UUID userId2;
    private UserPairId userPairId1;
    private UserPairId userPairId2;

    @BeforeEach
    void setUp() {
        userId1 = UUID.randomUUID();
        userId2 = UUID.randomUUID();

        userPairId1 = new UserPairId();
        userPairId1.setUserId1(userId1);
        userPairId1.setUserId2(userId2);

        userPairId2 = new UserPairId();
        userPairId2.setUserId1(userId2);
        userPairId2.setUserId2(userId1);
    }

    @Test
    void testEquals() {
        assertEquals(userPairId1, userPairId1);

        assertNotEquals(userPairId1, new Object());

        assertNotEquals(userPairId1, userPairId2);
        userPairId2.setUserId1(this.userId1);
        assertNotEquals(userPairId1, userPairId2);
        userPairId2.setUserId2(this.userId2);
        assertEquals(userPairId1, userPairId2);

        assertNotEquals(userPairId1, new UserPairStats());
    }

    @Test
    void testHashCode() {
        assertEquals(userPairId1.hashCode(), userPairId1.hashCode());
        assertNotEquals(userPairId1.hashCode(), new UserPairId().hashCode());
    }
}
