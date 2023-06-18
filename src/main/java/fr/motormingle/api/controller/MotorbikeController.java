package fr.motormingle.api.controller;

import fr.motormingle.api.controller.stereotype.GetController;
import fr.motormingle.api.entity.Motorbike;
import fr.motormingle.api.service.MotorbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/motorbike")
public class MotorbikeController implements GetController<Motorbike, Long> {

    @Autowired
    private MotorbikeService motorbikeService;

    @Override
    public ResponseEntity<List<Motorbike>> getAll() {
        List<Motorbike> motorbikes = motorbikeService.findAll();
        return ResponseEntity.ok(motorbikes);
    }

    @Override
    public ResponseEntity<Motorbike> getById(Long id) {
        Motorbike motorbike = motorbikeService.findById(id);
        return ResponseEntity.ok(motorbike);
    }
}
