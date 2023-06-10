package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FriendshipIdTest {

    @Test
    void testEquals() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        FriendshipId friendshipId1 = new FriendshipId();
        friendshipId1.setUserId1(userId1);
        friendshipId1.setUserId2(userId2);

        FriendshipId friendshipId2 = new FriendshipId();
        friendshipId2.setUserId1(userId1);
        friendshipId2.setUserId2(userId2);

        assertEquals(friendshipId1, friendshipId2);

        friendshipId2.setUserId2(UUID.randomUUID());
        assertNotEquals(friendshipId1, friendshipId2);
    }

    @Test
    void testHashCode() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        FriendshipId friendshipId1 = new FriendshipId();
        friendshipId1.setUserId1(userId1);
        friendshipId1.setUserId2(userId2);

        FriendshipId friendshipId2 = new FriendshipId();
        friendshipId2.setUserId1(userId1);
        friendshipId2.setUserId2(userId2);

        assertEquals(friendshipId1.hashCode(), friendshipId2.hashCode());

        friendshipId2.setUserId2(UUID.randomUUID());
        assertNotEquals(friendshipId1.hashCode(), friendshipId2.hashCode());
    }
}
