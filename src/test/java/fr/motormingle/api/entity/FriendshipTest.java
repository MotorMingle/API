package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FriendshipTest {

    private Friendship friendship1;
    private Friendship friendship2;
    private UserPairId userPairId1;
    private UserPairStats userPairStats1;

    @BeforeEach
    void setUp() {
        Mingler mingler1 = new Mingler();
        mingler1.setId("fakeId1");
        mingler1.setEmail("email1@example.com");

        Mingler mingler2 = new Mingler();
        mingler2.setId("fakeId2");
        mingler2.setEmail("email2@example.com");

        Mingler mingler3 = new Mingler();
        mingler3.setId("fakeId3");
        mingler3.setEmail("email3@example.com");

        friendship1 = new Friendship();
        userPairId1 = new UserPairId();
        userPairId1.setUserId1(mingler1.getId());
        userPairId1.setUserId2(mingler2.getId());
        friendship1.setId(userPairId1);
        friendship1.setMingler1(mingler1);
        friendship1.setMingler2(mingler2);
        userPairStats1 = new UserPairStats();
        userPairStats1.setDate(LocalDate.now().plusDays(1));
        userPairStats1.setUser1Status(EncounterStatus.DECLINED);
        userPairStats1.setUser2Status(EncounterStatus.DECLINED);
        friendship1.setUserPairStats(userPairStats1);

        friendship2 = new Friendship();
        UserPairId userPair2 = new UserPairId();
        userPair2.setUserId1(mingler1.getId());
        userPair2.setUserId2(mingler3.getId());
        friendship2.setId(userPair2);
        friendship2.setMingler1(mingler1);
        friendship2.setMingler2(mingler3);
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
        assertEquals(userPairId1.getUserId1(), friendship1.getMingler1().getId());
        assertEquals(userPairId1.getUserId2(), friendship1.getMingler2().getId());
        assertEquals(userPairStats1, friendship1.getUserPairStats());

        userPairStats1.setDate(LocalDate.now().plusDays(2));
        userPairStats1.setUser1Status(EncounterStatus.PENDING);
        userPairStats1.setUser2Status(EncounterStatus.PENDING);
        friendship1.setUserPairStats(userPairStats1);

        assertEquals(userPairStats1, friendship1.getUserPairStats());
    }
}
