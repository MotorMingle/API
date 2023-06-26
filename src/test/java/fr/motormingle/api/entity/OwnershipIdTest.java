package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OwnershipIdTest {

    private String userId1;
    private OwnershipId ownershipId1;
    private OwnershipId ownershipId2;

    @BeforeEach
    void setUp() {
        userId1 = "fakeId1";
        ownershipId1 = new OwnershipId();
        ownershipId1.setUserId(userId1);
        ownershipId1.setVehicleId(1L);

        ownershipId2 = new OwnershipId();
        ownershipId2.setUserId("fakeId2");
        ownershipId2.setVehicleId(2L);
    }

    @Test
    void testSetterAndGetter() {
        assertEquals(userId1, ownershipId1.getUserId());
        assertEquals(1L, ownershipId1.getVehicleId());
    }

    @Test
    void testEquals() {
        assertEquals(ownershipId1, ownershipId1);

        assertNotEquals(ownershipId1, new Object());

        assertNotEquals(ownershipId1, ownershipId2);
        ownershipId2.setVehicleId(1L);
        assertNotEquals(ownershipId1, ownershipId2);
        ownershipId2.setUserId(userId1);
        assertEquals(ownershipId1, ownershipId2);

        assertNotEquals(ownershipId1, new UserPairStats());
    }

    @Test
    void testHashCode() {
        assertEquals(ownershipId1.hashCode(), ownershipId1.hashCode());
        assertNotEquals(ownershipId1.hashCode(), new OwnershipIdTest().hashCode());
    }
}
