package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserPairTest {

    @Test
    void testEquals() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        UserPair userPair1 = new UserPair();
        userPair1.setUserId1(userId1);
        userPair1.setUserId2(userId2);

        UserPair userPair2 = new UserPair();
        userPair2.setUserId1(userId1);
        userPair2.setUserId2(userId2);

        assertEquals(userPair1, userPair2);

        userPair2.setUserId2(UUID.randomUUID());
        assertNotEquals(userPair1, userPair2);
    }

    @Test
    void testHashCode() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        UserPair userPair1 = new UserPair();
        userPair1.setUserId1(userId1);
        userPair1.setUserId2(userId2);

        UserPair userPair2 = new UserPair();
        userPair2.setUserId1(userId1);
        userPair2.setUserId2(userId2);

        assertEquals(userPair1.hashCode(), userPair2.hashCode());

        userPair2.setUserId2(UUID.randomUUID());
        assertNotEquals(userPair1.hashCode(), userPair2.hashCode());
    }
}
