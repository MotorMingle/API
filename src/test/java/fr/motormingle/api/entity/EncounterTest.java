package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncounterTest {

    private Encounter encounter1;
    private Encounter encounter2;
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

        encounter1 = new Encounter();
        userPairId1 = new UserPairId();
        userPairId1.setUserId1(mingler1.getId());
        userPairId1.setUserId2(mingler2.getId());
        encounter1.setId(userPairId1);
        encounter1.setMingler1(mingler1);
        encounter1.setMingler2(mingler2);
        encounter1.setHash("hash1");
        encounter1.setCount(1);
        userPairStats1 = new UserPairStats();
        userPairStats1.setDate(LocalDate.now());
        userPairStats1.setUser1Status(EncounterStatus.ACCEPTED);
        userPairStats1.setUser2Status(EncounterStatus.ACCEPTED);
        encounter1.setUserPairStats(userPairStats1);

        encounter2 = new Encounter();
        UserPairId userPairId2 = new UserPairId();
        userPairId2.setUserId1(mingler1.getId());
        userPairId2.setUserId2(mingler3.getId());
        encounter2.setId(userPairId2);
        encounter2.setMingler1(mingler1);
        encounter2.setMingler2(mingler3);
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
        assertEquals(userPairId1.getUserId1(), encounter1.getMingler1().getId());
        assertEquals(userPairId1.getUserId2(), encounter1.getMingler2().getId());
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
