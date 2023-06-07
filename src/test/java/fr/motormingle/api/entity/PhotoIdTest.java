package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PhotoIdTest {

    private PhotoId photoId1;
    private PhotoId photoId2;
    private OwnershipId ownershipId1;
    private OwnershipId ownershipId2;

    @BeforeEach
    void setUp() {
        ownershipId1 = new OwnershipId();
        ownershipId1.setUserId(UUID.randomUUID());
        ownershipId1.setVehicleId(1);

        ownershipId2 = new OwnershipId();
        ownershipId2.setUserId(UUID.randomUUID());
        ownershipId2.setVehicleId(2);

        photoId1 = new PhotoId();
        photoId1.setId(1L);
        photoId1.setOwnershipId(ownershipId1);

        photoId2 = new PhotoId();
        photoId2.setId(2L);
        photoId2.setOwnershipId(ownershipId2);
    }

    @Test
    void testSetterGetter() {
        assertEquals(1L, photoId1.getId());
        assertEquals(ownershipId1, photoId1.getOwnershipId());

        photoId1.setId(3L);
        photoId1.setOwnershipId(ownershipId2);

        assertEquals(3L, photoId1.getId());
        assertEquals(ownershipId2, photoId1.getOwnershipId());
    }

    @Test
    void testEquality() {
        assertNotEquals(photoId1, photoId2);
    }
}
