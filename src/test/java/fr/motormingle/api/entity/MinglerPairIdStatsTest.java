package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MinglerPairIdStatsTest {

    private UserPairStats userPairStats1;
    private UserPairStats userPairStats2;

    @BeforeEach
    void setUp() {
        userPairStats1 = new UserPairStats();
        userPairStats1.setDate(LocalDate.now());
        userPairStats1.setUser1Status(EncounterStatus.ACCEPTED);
        userPairStats1.setUser2Status(EncounterStatus.ACCEPTED);

        userPairStats2 = new UserPairStats();
        userPairStats2.setDate(LocalDate.now().minusDays(1));
        userPairStats2.setUser1Status(EncounterStatus.PENDING);
        userPairStats2.setUser2Status(EncounterStatus.PENDING);
    }

    @Test
    void testSettersAndGetters() {
        assertEquals(LocalDate.now(), userPairStats1.getDate());
        assertEquals(EncounterStatus.ACCEPTED, userPairStats1.getUser1Status());
        assertEquals(EncounterStatus.ACCEPTED, userPairStats1.getUser2Status());
    }

    @Test
    void testEquals() {
        assertEquals(userPairStats1, userPairStats1);

        assertNotEquals(userPairStats1, new Object());

        assertNotEquals(userPairStats1, userPairStats2);
        userPairStats2.setDate(LocalDate.now());
        assertNotEquals(userPairStats1, userPairStats2);
        userPairStats2.setUser1Status(EncounterStatus.ACCEPTED);
        assertNotEquals(userPairStats1, userPairStats2);
        userPairStats2.setUser2Status(EncounterStatus.ACCEPTED);
        assertEquals(userPairStats1, userPairStats2);

        assertNotEquals(userPairStats1, new UserPairStats());
    }

    @Test
    void testHashCode() {
        assertEquals(userPairStats1.hashCode(), userPairStats1.hashCode());
        assertNotEquals(userPairStats1.hashCode(), new UserPairStats().hashCode());
    }
}
