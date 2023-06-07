package fr.motormingle.api.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OwnershipIdTest {

    @Test
    void testEquals() {
        UUID userId = UUID.randomUUID();
        Integer vehicleId = 123;

        OwnershipId ownershipId1 = new OwnershipId();
        ownershipId1.setUserId(userId);
        ownershipId1.setVehicleId(vehicleId);

        OwnershipId ownershipId2 = new OwnershipId();
        ownershipId2.setUserId(userId);
        ownershipId2.setVehicleId(vehicleId);

        assertEquals(ownershipId1, ownershipId2);

        ownershipId2.setVehicleId(456);
        assertNotEquals(ownershipId1, ownershipId2);
    }

    @Test
    void testHashCode() {
        UUID userId = UUID.randomUUID();
        Integer vehicleId = 123;

        OwnershipId ownershipId1 = new OwnershipId();
        ownershipId1.setUserId(userId);
        ownershipId1.setVehicleId(vehicleId);

        OwnershipId ownershipId2 = new OwnershipId();
        ownershipId2.setUserId(userId);
        ownershipId2.setVehicleId(vehicleId);

        assertEquals(ownershipId1.hashCode(), ownershipId2.hashCode());

        ownershipId2.setVehicleId(456);
        assertNotEquals(ownershipId1.hashCode(), ownershipId2.hashCode());
    }
}
