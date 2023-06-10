package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FriendshipTest {

    private Friendship friendship1;
    private Friendship friendship2;

    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setEmail("email1@example.com");

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setEmail("email2@example.com");

        User user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setEmail("email3@example.com");

        friendship1 = new Friendship();
        FriendshipId friendshipId1 = new FriendshipId();
        friendshipId1.setUserId1(user1.getId());
        friendshipId1.setUserId2(user2.getId());
        friendship1.setId(friendshipId1);
        friendship1.setUserId1(user1);
        friendship1.setUserId2(user2);
        friendship1.setDate(LocalDate.now());
        friendship1.setUser1Status(EncounterStatus.ACCEPTED);
        friendship1.setUser2Status(EncounterStatus.ACCEPTED);

        friendship2 = new Friendship();
        FriendshipId friendshipId2 = new FriendshipId();
        friendshipId2.setUserId1(user1.getId());
        friendshipId2.setUserId2(user3.getId());
        friendship2.setId(friendshipId2);
        friendship2.setUserId1(user1);
        friendship2.setUserId2(user3);
        friendship2.setDate(LocalDate.now().plusDays(1));
        friendship2.setUser1Status(EncounterStatus.DECLINED);
        friendship2.setUser2Status(EncounterStatus.DECLINED);
    }

    @Test
    void testEquality() {
        assertNotEquals(friendship1, friendship2);
    }

    @Test
    void testSetterGetter() {
        assertEquals(LocalDate.now(), friendship1.getDate());
        assertEquals(EncounterStatus.ACCEPTED, friendship1.getUser1Status());
        assertEquals(EncounterStatus.ACCEPTED, friendship1.getUser2Status());

        friendship1.setDate(LocalDate.now().plusDays(2));
        friendship1.setUser1Status(EncounterStatus.PENDING);
        friendship1.setUser2Status(EncounterStatus.PENDING);

        assertEquals(LocalDate.now().plusDays(2), friendship1.getDate());
        assertEquals(EncounterStatus.PENDING, friendship1.getUser1Status());
        assertEquals(EncounterStatus.PENDING, friendship1.getUser2Status());
    }
}
