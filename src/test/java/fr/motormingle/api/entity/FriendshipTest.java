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
    private UserPairId userPairId1;
    private UserPairStats userPairStats1;

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
        userPairId1 = new UserPairId();
        userPairId1.setUserId1(user1.getId());
        userPairId1.setUserId2(user2.getId());
        friendship1.setId(userPairId1);
        friendship1.setUserId1(user1);
        friendship1.setUserId2(user2);
        userPairStats1 = new UserPairStats();
        userPairStats1.setDate(LocalDate.now().plusDays(1));
        userPairStats1.setUser1Status(EncounterStatus.DECLINED);
        userPairStats1.setUser2Status(EncounterStatus.DECLINED);
        friendship1.setUserPairStats(userPairStats1);

        friendship2 = new Friendship();
        UserPairId userPairId2 = new UserPairId();
        userPairId2.setUserId1(user1.getId());
        userPairId2.setUserId2(user3.getId());
        friendship2.setId(userPairId2);
        friendship2.setUserId1(user1);
        friendship2.setUserId2(user3);
        UserPairStats userPairStats2 = new UserPairStats();
        userPairStats2.setDate(LocalDate.now().plusDays(1));
        userPairStats2.setUser1Status(EncounterStatus.DECLINED);
        userPairStats2.setUser2Status(EncounterStatus.DECLINED);
        friendship2.setUserPairStats(userPairStats2);
    }

    @Test
    void testEquality() {
        assertNotEquals(friendship1, friendship2);
    }

    @Test
    void testSetterGetter() {
        assertEquals(userPairId1, friendship1.getId());
        assertEquals(userPairId1.getUserId1(), friendship1.getUserId1().getId());
        assertEquals(userPairId1.getUserId2(), friendship1.getUserId2().getId());
        assertEquals(userPairStats1, friendship1.getUserPairStats());

        userPairStats1.setDate(LocalDate.now().plusDays(2));
        userPairStats1.setUser1Status(EncounterStatus.PENDING);
        userPairStats1.setUser2Status(EncounterStatus.PENDING);
        friendship1.setUserPairStats(userPairStats1);

        assertEquals(userPairStats1, friendship1.getUserPairStats());
    }
}
