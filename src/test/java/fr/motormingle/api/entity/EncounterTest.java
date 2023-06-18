package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncounterTest {

    private Encounter encounter1;
    private Encounter encounter2;
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

        encounter1 = new Encounter();
        userPairId1 = new UserPairId();
        userPairId1.setUserId1(user1.getId());
        userPairId1.setUserId2(user2.getId());
        encounter1.setId(userPairId1);
        encounter1.setUserId1(user1);
        encounter1.setUserId2(user2);
        encounter1.setHash("hash1");
        encounter1.setCount(1);
        userPairStats1 = new UserPairStats();
        userPairStats1.setDate(LocalDate.now());
        userPairStats1.setUser1Status(EncounterStatus.ACCEPTED);
        userPairStats1.setUser2Status(EncounterStatus.ACCEPTED);
        encounter1.setUserPairStats(userPairStats1);

        encounter2 = new Encounter();
        UserPairId userPairId2 = new UserPairId();
        userPairId2.setUserId1(user1.getId());
        userPairId2.setUserId2(user3.getId());
        encounter2.setId(userPairId2);
        encounter2.setUserId1(user1);
        encounter2.setUserId2(user3);
        encounter2.setHash("hash2");
        encounter2.setCount(2);
        UserPairStats userPairStats2 = new UserPairStats();
        userPairStats2.setDate(LocalDate.now().plusDays(1));
        userPairStats2.setUser1Status(EncounterStatus.DECLINED);
        userPairStats2.setUser2Status(EncounterStatus.DECLINED);
        encounter2.setUserPairStats(userPairStats2);
    }

    @Test
    void testEquality() {
        assertNotEquals(encounter1, encounter2);
    }

    @Test
    void testSetterGetter() {
        assertEquals(userPairId1, encounter1.getId());
        assertEquals(userPairId1.getUserId1(), encounter1.getUserId1().getId());
        assertEquals(userPairId1.getUserId2(), encounter1.getUserId2().getId());
        assertEquals("hash1", encounter1.getHash());
        assertEquals(1, encounter1.getCount());
        assertEquals(userPairStats1, encounter1.getUserPairStats());

        encounter1.setHash("newhash");
        encounter1.setCount(3);
        userPairStats1.setDate(LocalDate.now().plusDays(2));
        userPairStats1.setUser1Status(EncounterStatus.PENDING);
        userPairStats1.setUser2Status(EncounterStatus.PENDING);
        encounter1.setUserPairStats(userPairStats1);

        assertEquals("newhash", encounter1.getHash());
        assertEquals(3, encounter1.getCount());
        assertEquals(userPairStats1, encounter1.getUserPairStats());
    }
}
