package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OwnershipTest {

    private Ownership ownership1;
    private Ownership ownership2;
    private Mingler mingler1;
    private Mingler mingler2;
    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeEach
    void setUp() {
        mingler1 = new Mingler();
        mingler1.setId(UUID.randomUUID());
        mingler1.setEmail("user1@example.com");

        mingler2 = new Mingler();
        mingler2.setId(UUID.randomUUID());
        mingler2.setEmail("user2@example.com");

        vehicle1 = new Car();
        vehicle1.setId(1L);

        vehicle2 = new Car();
        vehicle2.setId(2L);

        OwnershipId ownershipId1 = new OwnershipId();
        ownershipId1.setUserId(mingler1.getId());
        ownershipId1.setVehicleId(vehicle1.getId());

        OwnershipId ownershipId2 = new OwnershipId();
        ownershipId2.setUserId(mingler2.getId());
        ownershipId2.setVehicleId(vehicle2.getId());

        ownership1 = new Ownership();
        ownership1.setId(ownershipId1);
        ownership1.setMingler(mingler1);
        ownership1.setVehicle(vehicle1);

        ownership2 = new Ownership();
        ownership2.setId(ownershipId2);
        ownership2.setMingler(mingler2);
        ownership2.setVehicle(vehicle2);
    }

    @Test
    void testSetterGetter() {
        assertEquals(mingler1, ownership1.getMingler());
        assertEquals(vehicle1, ownership1.getVehicle());

        ownership1.setMingler(mingler2);
        ownership1.setVehicle(vehicle2);

        assertEquals(mingler2, ownership1.getMingler());
        assertEquals(vehicle2, ownership1.getVehicle());
    }

    @Test
    void testEquality() {
        assertNotEquals(ownership1, ownership2);
    }
}
