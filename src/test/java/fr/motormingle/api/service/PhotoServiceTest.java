package fr.motormingle.api.service;

import fr.motormingle.api.entity.*;
import fr.motormingle.api.repository.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PhotoServiceTest {

    @InjectMocks
    private PhotoService photoService;

    @Mock
    private PhotoRepository photoRepository;

    private Photo photo;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);

        Mingler mingler = new Mingler();
        mingler.setId(UUID.randomUUID());
        mingler.setEmail("test@test.com");

        Ownership ownership = new Ownership();
        OwnershipId ownershipId = new OwnershipId();
        ownershipId.setUserId(mingler.getId());
        ownershipId.setVehicleId(1L);
        ownership.setId(ownershipId);
        ownership.setMingler(mingler);

        photo = new Photo();
        PhotoId photoId = new PhotoId();
        photoId.setId(1L);
        photoId.setOwnershipId(ownershipId);
        photo.setId(photoId);
        photo.setOwnership(ownership);
        Blob blob = new SerialBlob(new byte[]{1, 2, 3});
        photo.setContent(blob);
    }

    @Test
    void testFindAll() {
        when(photoRepository.findAll()).thenReturn(Collections.singletonList(photo));

        List<Photo> result = photoService.findAll();
        assertEquals(1, result.size());
        assertEquals(photo, result.get(0));
    }

    @Test
    void testFindById() {
        when(photoRepository.findById(any(PhotoId.class))).thenReturn(Optional.of(photo));

        Photo result = photoService.findById(photo.getId());
        assertEquals(photo, result);
    }
}
