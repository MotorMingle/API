package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PhotoIdTest {

    private PhotoId photoId1;
    private PhotoId photoId2;
    private OwnershipId ownershipId1;

    @BeforeEach
    void setUp() {
        ownershipId1 = new OwnershipId();
        ownershipId1.setUserId("fakeId1");
        ownershipId1.setVehicleId(1L);

        OwnershipId ownershipId2 = new OwnershipId();
        ownershipId2.setUserId("fakeId2");
        ownershipId2.setVehicleId(2L);

        photoId1 = new PhotoId();
        photoId1.setId(1L);
        photoId1.setOwnershipId(ownershipId1);

        photoId2 = new PhotoId();
        photoId2.setId(2L);
        photoId2.setOwnershipId(ownershipId2);
    }

    @Test
    void testSetterAndGetter() {
        assertEquals(1L, photoId1.getId());
        assertEquals(ownershipId1, photoId1.getOwnershipId());
    }

    @Test
    void testEquals() {
        assertEquals(photoId1, photoId1);

        assertNotEquals(photoId1, new Object());

        assertNotEquals(photoId1, photoId2);
        photoId2.setId(1L);
        assertNotEquals(photoId1, photoId2);
        photoId2.setOwnershipId(ownershipId1);
        assertEquals(photoId1, photoId2);

        assertNotEquals(photoId1, new PhotoId());
    }

    @Test
    void testHashCode() {
        assertEquals(photoId1.hashCode(), photoId1.hashCode());
        assertNotEquals(photoId1.hashCode(), new PhotoId().hashCode());
    }
}
